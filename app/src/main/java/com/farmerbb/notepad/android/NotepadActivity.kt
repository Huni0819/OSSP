/* Copyright 2021 Braden Farmer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")

package com.farmerbb.notepad.android

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.farmerbb.notepad.ui.routes.NotepadComposeAppRoute
import com.farmerbb.notepad.viewmodel.NotepadViewModel
import com.github.k1rakishou.fsaf.FileChooser
import com.github.k1rakishou.fsaf.callback.FSAFActivityCallbacks
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotepadActivity: ComponentActivity(), FSAFActivityCallbacks {
    private val vm: NotepadViewModel by viewModel()
    private val fileChooser: FileChooser = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fileChooser.setCallbacks(this)

        vm.migrateData {
            setContent {
                NotepadComposeAppRoute()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        vm.deleteDraft()
    }

    override fun onStop() {
        super.onStop()
        if (!isChangingConfigurations) {
            vm.saveDraft()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fileChooser.removeCallbacks()
    }


    override fun fsafStartActivityForResult(intent: Intent, requestCode: Int) {
        when(intent.action) {
            //인텐트 작업을 통해 사용자는 파일을 특정 위치에 저장
            Intent.ACTION_OPEN_DOCUMENT -> intent.type = "text/plain"
            //인텐트 작업을 통해 사용자는 특정 디렉터리를 선택하여 이 디렉터리 내의 모든 파일 및 하위 디렉터리에 관한 액세스 권한을 앱에 부여
            Intent.ACTION_OPEN_DOCUMENT_TREE -> intent.removeExtra(Intent.EXTRA_LOCAL_ONLY)
        }
        
        //특정 액티비티 실행
        startActivityForResult(intent, requestCode)
    }

    //액티비티 실행 후 main액티비티로 돌아올때 사용
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        fileChooser.onActivityResult(requestCode, resultCode, data)
    }

    //단축키를 구현
    override fun dispatchKeyShortcutEvent(event: KeyEvent): Boolean {
        return if (event.action == KeyEvent.ACTION_DOWN && event.isCtrlPressed) {
            vm.keyboardShortcutPressed(event.keyCode)
        } else {
            super.dispatchKeyShortcutEvent(event)
        }
    }
}