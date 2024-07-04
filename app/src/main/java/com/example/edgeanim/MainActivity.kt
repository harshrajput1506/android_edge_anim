package com.example.edgeanim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edgeanim.ui.theme.EdgeAnimTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EdgeAnimTheme {
                Box (
                    modifier = Modifier.fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    AnimatedSurface()
                }
            }
        }
    }
}

@Composable
fun AnimatedSurface() {
    var checked by remember {
        mutableStateOf(false)
    }
    val infiniteTransition = rememberInfiniteTransition("edge animation")
    val degrees by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "edge"
    )
    val brush = Brush.verticalGradient(colors = listOf(Color.Blue, Color.Cyan, Color.Magenta), tileMode = TileMode.Repeated)
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .drawWithContent {
                if (checked) {
                    rotate(degrees = degrees) {
                        drawCircle(
                            brush = brush,
                            radius = size.height,
                            blendMode = BlendMode.SrcIn
                        )
                    }
                }
                drawContent()
            }
    ) {

        Scaffold (
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = " Edge Light Animation",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )

                Switch(
                    checked = checked,
                    onCheckedChange = {checked = it}
                )
            }
        }

    }
}

@Composable
fun EdgeLightAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedValue = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val strokeWidth = 10.dp.toPx()
        val length = size.width * animatedValue.value

        // Top edge
        drawLine(
            color = Color.Magenta,
            start = Offset(0f, 0f),
            end = Offset(length, 0f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // Bottom edge
        drawLine(
            color = Color.Magenta,
            start = Offset(0f, size.height),
            end = Offset(length, size.height),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // Right edge
        drawLine(
            color = Color.Magenta,
            start = Offset(size.width, 0f),
            end = Offset(size.width, length),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // Left edge
        drawLine(
            color = Color.Magenta,
            start = Offset(0f, 0f),
            end = Offset(0f, length),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}


