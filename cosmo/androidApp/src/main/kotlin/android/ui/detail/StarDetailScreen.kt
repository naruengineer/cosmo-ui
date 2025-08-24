// StarDetailScreen.kt
package com.yourcompany.cosmo.android.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StarDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Star") },
                navigationIcon = { TextButton(onClick = onBack){ Text("Back") } }
            )
        }
    ) { pad ->
        Column(Modifier.padding(pad).padding(20.dp)) {
            Text("Title: Placeholder")
            Text("Genre: Sci")
            Text("Reason: Similar theme to …")
        }
    }
}
