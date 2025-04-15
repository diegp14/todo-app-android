package com.dguzman.todoapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun NotaInputText(
    text: String,
    label: String,
    maxLines : Int,
    focusRequester: FocusRequester,
    onValueChange: (String) -> Unit,
){
   val keyController = LocalSoftwareKeyboardController.current


   TextField(
       modifier = Modifier.fillMaxWidth().padding(15.dp).focusRequester(focusRequester),
       value = text,
       onValueChange = onValueChange,
       maxLines = maxLines,
       label = { Text(text = label) },
       keyboardOptions = KeyboardOptions.Default.copy(
           imeAction = ImeAction.Done
       ),
       keyboardActions = KeyboardActions(
           onDone = {
               // Handle the done action
                keyController?.hide()

           }
       ),
   )
}

@Composable
fun NotaButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,

) {
    Button(
        onClick = onClick,
        enabled = enabled
    ) {
        Text(text = text)
    }
}