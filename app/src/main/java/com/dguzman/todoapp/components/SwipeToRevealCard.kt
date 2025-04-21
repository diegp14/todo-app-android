package com.dguzman.todoapp.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SwipeToRevealCard(
    modifier: Modifier = Modifier,
    //onEdit: () -> Unit,
    //onDelete: () -> Unit,
    actionButtons: List<@Composable () -> Unit>,
    //onExpanded: () -> Unit,
    //onCollapsed: () -> Unit,
    content: @Composable () -> Unit,


    ) {
    val swipeOffset = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val totalButtonWidth = remember { mutableStateOf(0) }

    BoxWithConstraints(modifier = modifier) {
        val boxWidth = constraints.maxWidth

        SubcomposeLayout { constraints ->
            val buttonPlaceables = actionButtons.mapIndexed { index, action ->
                val measurable = subcompose("button_$index") {
                    Box(
                        Modifier.wrapContentWidth(unbounded = true)
                    ) {
                        action()
                    }
                }.first()
                measurable.measure(constraints)
            }

            val totalWidth = buttonPlaceables.sumOf { it.width }
            totalButtonWidth.value = totalWidth
            layout(0, 0) {}
        }

        Box(modifier = modifier.clip(RoundedCornerShape(16.dp))) {
            // FONDO CON BOTONES
            Row(
                modifier = Modifier
                    .matchParentSize()
                   // .clip(shape = RoundedCornerShape(15.dp))
                    .background(Color.DarkGray),
                horizontalArrangement = Arrangement.End
            ) {
                actionButtons.forEach { actionButton ->
                    actionButton()

                }
            }
            // CARD DESLIZABLE
            Card(
                modifier = Modifier
                    .offset { IntOffset(swipeOffset.value.roundToInt(), 0) }
                    .pointerInput(actionButtons.size) {
                        detectHorizontalDragGestures(
                            onHorizontalDrag = { _, dragAmount ->
                                scope.launch {

                                    val newOffset = (swipeOffset.value + dragAmount).coerceIn(
                                        -totalButtonWidth.value.toFloat(),
                                        0f
                                    )
                                    swipeOffset.snapTo(newOffset)
                                }
                            },
                            onDragEnd = {
                                scope.launch {
                                    if (swipeOffset.value < -totalButtonWidth.value / 2f) {
                                        swipeOffset.animateTo(-totalButtonWidth.value.toFloat())
                                        //onExpanded()
                                    } else {
                                        swipeOffset.animateTo(0f)
                                        //onCollapsed()
                                    }
                                }
                            }
                        )
                    }
                    .background(Color.White)
            ) {
                content()
            }
        }
    }
}