package com.dguzman.todoapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogCustom(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    contentDescription: String? = null,
    onCancel: () -> Unit = {},
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ){
        Surface(
            modifier = Modifier.wrapContentWidth().wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(50.dp)
                )
                Text(
                    text = dialogTitle,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                        .wrapContentWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = dialogText,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.align(Alignment.End),
                ) {
                    TextButton (
                        onClick = { onCancel() },
                        //modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Cancelar", color = Color(0xFF0A71E3))
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        // modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Confirmar", color = Color(0xFFD00227))
                    }
                }
            }
        }
    }
}