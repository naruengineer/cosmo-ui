// WarpScreen.kt
package com.yourcompany.cosmo.android.ui.warp

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun WarpScreen(
    onWarpFinished: () -> Unit
) {
    val t by rememberInfiniteTransition().animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1200, easing = LinearEasing))
    )

    // 2秒で完了 → 戻る
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        onWarpFinished()
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val n = 160
        repeat(n) { i ->
            val p = i.toFloat() / n
            val alpha = (0.2f + 0.8f * p)
            drawLine(
                color = Color(0xFF7A5CF5).copy(alpha = alpha),
                start = center,
                end = center + androidx.compose.ui.geometry.Offset(
                    x = size.minDimension * p * (1f + t),
                    y = 0f
                ),
                strokeWidth = 2f * (1f + p)
            )
            rotate(360f / n * i) { /* 放射状ライン */ }
        }
    }
}
