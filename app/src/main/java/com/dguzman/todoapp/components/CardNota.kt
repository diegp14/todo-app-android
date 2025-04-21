package com.dguzman.todoapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dguzman.todoapp.models.Nota
import com.dguzman.todoapp.utils.Converters
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date


@Composable
fun CardNota(
    nota: Nota,
    onRemoveNota: (Nota) -> Unit = {},
    onClickNota: (Int) -> Unit = {},

    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            onClickNota(nota.id)
        }) {
        Column(modifier = Modifier.padding(15.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = nota.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                )
                Text(
                    text = Converters.formatDate(nota.date.time),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    modifier = Modifier.padding(start = 10.dp)
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = nota.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(15.dp)
                )
                }
            }
        }

}


@Preview(showBackground = true)
@Composable
fun CardNotaPreview() {
    CardNota(
        nota = Nota(
            title = "Nota de prueba",
            description = "Descripción de la nota de prueba fdsfspd{fdñfdf{dfdfdjfdf{fdjfsdfkdfdkfdhfdjkfdfñdsfdfjdfdfhñdffherireñsei"
        )
    )
}
