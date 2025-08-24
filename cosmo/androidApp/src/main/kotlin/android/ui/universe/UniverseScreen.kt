// UniverseScreen.kt
package cosmo.android.ui.universe

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cosmo.android.ui.universe.components.StarField
import cosmo.android.fake.fakeStarsForGalaxy
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward

@Composable
fun UniverseScreen(
    onStarClick: (String) -> Unit,
    onWarpClick: (String) -> Unit
) {
    val stars by remember { mutableStateOf(fakeStarsForGalaxy("orion")) }

    Scaffold(
        topBar = { SmallTopAppBar(title = { Text("COSMO · Orion") }) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onWarpClick("andromeda") },
                text = { Text("Warp") },
                icon = { Icon(Icons.Default.ArrowForward, contentDescription = null) }
            )
        }
    ) { pad ->
        StarField(
            stars = stars,
            modifier = Modifier
                .fillMaxSize()
                .padding(pad),
            onStarClick = onStarClick
        )
    }
}
