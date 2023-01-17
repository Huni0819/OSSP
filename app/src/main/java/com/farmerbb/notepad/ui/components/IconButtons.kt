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

package com.farmerbb.notepad.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.farmerbb.notepad.R

//돌아가기 버튼
@Composable
fun BackButton(onClick: () -> Unit = {}) {
    val imageVector = when(LocalLayoutDirection.current) {
        LayoutDirection.Rtl -> Icons.Filled.ArrowForward
        else -> Icons.Filled.ArrowBack
    }

    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Color.White
        )
    }
}

//작성 버튼
@Composable
fun EditButton(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = stringResource(R.string.action_edit),
            tint = Color.White
        )
    }
}

//저장 버튼
@Composable
fun SaveButton(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Save,
            contentDescription = stringResource(R.string.action_save),
            tint = Color.White
        )
    }
}

//삭제 버튼
@Composable
fun DeleteButton(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.action_delete),
            tint = Color.White
        )
    }
}

//더보기 버튼
@Composable
fun MoreButton(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = null,
            tint = Color.White
        )
    }
}

//선택버튼
@Composable
fun MultiSelectButton(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.PlaylistAddCheck,
            contentDescription = stringResource(R.string.action_start_selection),
            tint = Color.White
        )
    }
}

//전체선택 버튼
@Composable
fun SelectAllButton(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.SelectAll,
            contentDescription = stringResource(R.string.action_select_all),
            tint = Color.White
        )
    }
}

//내보내기 버튼
@Composable
fun ExportButton(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.SdCard,
            contentDescription = stringResource(R.string.action_export),
            tint = Color.White
        )
    }
}

//중요함 체크 버튼
@Composable
fun ImportantButton(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = stringResource(R.string.action_export),
            tint = Color.Black
        )
    }
}