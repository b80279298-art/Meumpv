/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */

package app.infinity.mpvz.ui.player.controls.components.sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.infinity.mpvz.R

@Composable
fun ExternalAudioSourceDialog(
  onPickFile: () -> Unit,
  onOpenUrl: () -> Unit,
  onDismissRequest: () -> Unit,
) {
  AlertDialog(
    onDismissRequest = onDismissRequest,
    title = { Text(stringResource(R.string.player_sheets_add_ext_audio)) },
    text = {
      Column {
        OutlinedButton(
          onClick = onPickFile,
          modifier = Modifier.fillMaxWidth(),
        ) {
          Text(stringResource(R.string.player_sheets_pick_file))
        }
        OutlinedButton(
          onClick = onOpenUrl,
          modifier =
            Modifier
              .fillMaxWidth()
              .padding(top = 8.dp),
        ) {
          Text(stringResource(R.string.player_sheets_open_url))
        }
      }
    },
    confirmButton = {},
  )
}