package com.vaddy.filevault.presentation.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vaddy.filevault.viewmodel.FileViewModel
import com.vaddy.filevault.viewmodel.UserViewModel
import com.vaddy.filevault.viewmodel.factory.FileViewModelFactory


@Composable
fun UserScreen(navController: NavController) {
    val context = LocalContext.current
    val fileViewModel: FileViewModel = viewModel(factory = FileViewModelFactory(context.applicationContext as Application))
    val allFiles by fileViewModel.allFiles.observeAsState(listOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("User Dashboard", style = MaterialTheme.typography.headlineMedium)
        LazyColumn {
            items(allFiles) { file ->
                Text("File: ${file.fileName}")
            }
        }
    }
}
