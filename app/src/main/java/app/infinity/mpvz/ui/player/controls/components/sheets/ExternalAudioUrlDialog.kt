/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */

package app.infinity.mpvz.ui.player.controls.components.sheets

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import app.infinity.mpvz.R

@Composable
fun ExternalAudioUrlDialog(
  onConfirm: (String) -> Unit,
  onDismissRequest: () -> Unit,
) {
  var url by rememberSaveable { mutableStateOf("") }
  var submitted by remember { mutableStateOf(false) }
  val normalizedUrl = url.trim()
  val validUrl = isHttpUrl(normalizedUrl)

  AlertDialog(
    onDismissRequest = onDismissRequest,
    title = { Text(stringResource(R.string.player_sheets_audio_url_title)) },
    text = {
      OutlinedTextField(
        value = url,
        onValueChange = { url = it },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        isError = submitted && !validUrl,
        placeholder = {
          Text(stringResource(R.string.player_sheets_audio_url_placeholder))
        },
        supportingText = {
          if (submitted && !validUrl) {
            Text(stringResource(R.string.player_sheets_audio_url_invalid))
          }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
      )
    },
    dismissButton = {
      TextButton(onClick = onDismissRequest) {
        Text(stringResource(R.string.generic_cancel))
      }
    },
    confirmButton = {
      TextButton(
        onClick = {
          submitted = true
          if (validUrl) onConfirm(normalizedUrl)
        },
      ) {
        Text(stringResource(R.string.generic_ok))
      }
    },
  )
}

private fun isHttpUrl(value: String): Boolean {
  if (value.isBlank()) return false
  val uri = runCatching { Uri.parse(value) }.getOrNull() ?: return false
  return uri.scheme?.lowercase() in setOf("http", "https") && !uri.host.isNullOrBlank()
}