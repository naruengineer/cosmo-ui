// StarField.kt（パララックス3層＋クリック）
package cosmo.android.ui.universe.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.roundToInt
import cosmo.android.fake.Star

@Composable
fun StarField(
    stars: List<Star>,
    modifier: Modifier = Modifier,
    onStarClick: (String) -> Unit
) {
    var scale by remember { mutableStateOf(1f) }
    var pan by remember { mutableStateOf(Offset.Zero) }

    // 微小ドリフト（宇宙感）
    val drift by rememberInfiniteTransition().animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(6000, easing = LinearEasing))
    )

    Canvas(
        modifier = modifier.pointerInput(Unit) {
            detectTransformGestures { _, panChange, zoom, _ ->
                pan += panChange
                scale = (scale * zoom).coerceIn(0.5f, 2.5f)
            }
        }
    ) {
        val size = this.size
        // レイヤー深度ごとに描画（遠:小さく・薄く、近:大きく・明るく）
        fun layer(alpha: Float, sizeMul: Float, parallax: Float) {
            stars.forEachIndexed { i, s ->
                val base = Offset(
                    x = size.width  * (0.5f + s.x),
                    y = size.height * (0.5f + s.y)
                )
                val p = (base + pan * parallax) * scale + Offset(drift * parallax * 10f, 0f)
                val r = (1.5f + (i % 3)) * sizeMul * scale
                drawCircle(
                    color = Color.White.copy(alpha = alpha),
                    radius = r,
                    center = p
                )
            }
        }
        layer(alpha = 0.28f, sizeMul = 0.6f, parallax = 0.4f) // 遠
        layer(alpha = 0.45f, sizeMul = 0.9f, parallax = 0.7f) // 中
        layer(alpha = 0.75f, sizeMul = 1.2f, parallax = 1.0f) // 近
    }
    // ★ 星クリック判定は実運用では QuadTree などを使うのが安全。
    // MVPでは Canvas 上の簡易ヒットテストや、Individual Composable配置でもOK。
}
