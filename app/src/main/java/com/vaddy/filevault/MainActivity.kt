package com.vaddy.filevault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vaddy.filevault.navigation.FileVaultApp
import com.vaddy.filevault.ui.theme.FileVaultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FileVaultTheme {
                FileVaultApp()
            }
        }
    }
}

