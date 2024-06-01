package com.vaddy.filevault.presentation.screens

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vaddy.filevault.viewmodel.AccessLogViewModel
import com.vaddy.filevault.utils.viewmodelfactory.AccessLogViewModelFactory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Date

@Composable
fun AdminLogScreen(navController: NavController) {
    val context = LocalContext.current
    val accessLogViewModel: AccessLogViewModel = viewModel(factory = AccessLogViewModelFactory(context.applicationContext as Application))
    val allLogs by accessLogViewModel.getAllLogs().observeAsState(listOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Access Logs", style = MaterialTheme.typography.headlineMedium)
        LazyColumn {
            items(allLogs) { log ->
                Text("User: ${log.userId} accessed File: ${log.fileId} at ${Date(log.accessTime).toString()}")
            }
        }
    }
}

