// MainActivity.kt
package com.yourcompany.cosmo.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cosmo.android.nav.CosmoNavGraph
import cosmo.android.theme.CosmoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CosmoTheme {
                CosmoNavGraph()
            }
        }
    }
}
