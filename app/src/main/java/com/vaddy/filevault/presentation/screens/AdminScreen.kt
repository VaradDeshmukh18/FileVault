package com.vaddy.filevault.presentation.screens

import android.app.Application
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vaddy.filevault.data.model.File
import com.vaddy.filevault.data.model.FileAccess
import com.vaddy.filevault.utils.viewmodelfactory.FileAccessViewModelFactory
import com.vaddy.filevault.utils.viewmodelfactory.FileViewModelFactory
import com.vaddy.filevault.utils.viewmodelfactory.UserViewModelFactory
import com.vaddy.filevault.viewmodel.FileAccessViewModel
import com.vaddy.filevault.viewmodel.FileViewModel
import com.vaddy.filevault.viewmodel.UserViewModel
import java.util.Date


@Composable
fun AdminScreen(navController: NavController) {
    val context = LocalContext.current
    val fileViewModel: FileViewModel = viewModel(factory = FileViewModelFactory(context.applicationContext as Application))
    val fileAccessViewModel: FileAccessViewModel = viewModel(factory = FileAccessViewModelFactory(context.applicationContext as Application))
    val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(context.applicationContext as Application))
    val allFiles by fileViewModel.allFiles.observeAsState(listOf())
    val allUsers by userViewModel.allUsers.observeAsState(listOf())

    val openFilePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(uri)
            val fileBytes = inputStream?.readBytes() ?: ByteArray(0)
            val fileName = getFileName(context, uri)
            val fileDescription = "File uploaded by admin" // Modify as needed
            val fileSize = fileBytes.size.toLong()
            val encryptionTime = System.currentTimeMillis()

            val newFile = File(
                fileName = fileName,
                fileSize = fileSize,
                description = fileDescription,
                encryptionTime = encryptionTime,
                encryptedData = fileBytes
            )

            fileViewModel.insertFile(newFile, String(fileBytes))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Admin Dashboard", style = MaterialTheme.typography.headlineMedium)
        LazyColumn {
            items(allFiles) { file ->
                Column {
                    Text("File: ${file.fileName}")
                    Text("Size: ${file.fileSize} bytes")
                    Text("Description: ${file.description}")
                    Text("Encryption Time: ${Date(file.encryptionTime).toString()}")

                    // Show all users and grant/revoke access
                    LazyColumn {
                        items(allUsers) { user ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(user.email)
                                Button(onClick = {
                                    val fileAccess = FileAccess(userId = user.userId, fileId = file.fileId)
                                    fileAccessViewModel.insertFileAccess(fileAccess)
                                }) {
                                    Text("Grant Access")
                                }
                                Button(onClick = {
                                    fileAccessViewModel.revokeAccess(user.userId, file.fileId)
                                }) {
                                    Text("Revoke Access")
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { openFilePicker.launch("*/*") }) {
            Text("Upload New File")
        }
    }
}

fun getFileName(context: Context, uri: Uri): String {
    var result: String? = null
    if (uri.scheme == "content") {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (columnIndex >= 0) { // Check if the column exists
                    result = it.getString(columnIndex)
                }
            }
        }
    }
    if (result == null) {
        result = uri.path
        val cut = result?.lastIndexOf('/')
        if (cut != null && cut != -1) {
            result = result?.substring(cut + 1)
        }
    }
    return result ?: "Unknown"
}


