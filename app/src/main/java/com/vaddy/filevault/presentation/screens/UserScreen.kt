package com.vaddy.filevault.presentation.screens

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vaddy.filevault.viewmodel.FileAccessViewModel
import com.vaddy.filevault.viewmodel.FileViewModel
import com.vaddy.filevault.utils.viewmodelfactory.FileAccessViewModelFactory
import com.vaddy.filevault.utils.viewmodelfactory.FileViewModelFactory
import java.io.File


@Composable
fun UserScreen(navController: NavController, userId: Int) {
    val context = LocalContext.current
    val fileViewModel: FileViewModel = viewModel(factory = FileViewModelFactory(context.applicationContext as Application))
    val fileAccessViewModel: FileAccessViewModel = viewModel(factory = FileAccessViewModelFactory(context.applicationContext as Application))
    val allFiles by fileViewModel.allFiles.observeAsState(listOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("User Dashboard", style = MaterialTheme.typography.headlineMedium)
        LazyColumn {
            items(allFiles) { file ->
                Column {
                    Text("File: ${file.fileName}")
                    Button(onClick = {
                        fileAccessViewModel.getFileAccess(userId, file.fileId).observe(context as LifecycleOwner) { fileAccess ->
                            if (fileAccess != null) {
                                val decryptedContent = fileViewModel.decryptFile(file)
                                saveFile(context, file.fileName, decryptedContent)
                            } else {
                                Toast.makeText(context, "Access denied", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }) {
                        Text("Download")
                    }
                }
            }
        }
    }
}

fun saveFile(context: Context, fileName: String, content: String) {
    val file = File(context.getExternalFilesDir(null), fileName)
    file.writeText(content)
    Toast.makeText(context, "File saved to ${file.absolutePath}", Toast.LENGTH_SHORT).show()
}

