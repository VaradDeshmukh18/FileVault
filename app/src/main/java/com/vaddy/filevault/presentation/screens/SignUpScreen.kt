package com.vaddy.filevault.presentation.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vaddy.filevault.data.model.Admin
import com.vaddy.filevault.data.model.User
import com.vaddy.filevault.utils.viewmodelfactory.AdminViewModelFactory
import com.vaddy.filevault.viewmodel.UserViewModel
import com.vaddy.filevault.utils.viewmodelfactory.UserViewModelFactory
import com.vaddy.filevault.viewmodel.AdminViewModel


@Composable
fun SignupScreen(navController: NavController, isAdmin: Boolean) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val context = LocalContext.current

    if (isAdmin) {
        val adminViewModel: AdminViewModel = viewModel(factory = AdminViewModelFactory(context.applicationContext as Application))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (password == confirmPassword) {
                    adminViewModel.insertAdmin(Admin(email = email, password = password))
                    Toast.makeText(context, "Admin registered successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate("loginAdmin")
                } else {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Sign Up")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("loginAdmin") }) {
                Text("Back to Login")
            }
        }
    } else {
        val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(context.applicationContext as Application))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (password == confirmPassword) {
                    userViewModel.insertUser(User(email = email, password = password))
                    Toast.makeText(context, "User registered successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate("loginUser")
                } else {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Sign Up")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("loginUser") }) {
                Text("Back to Login")
            }
        }
    }
}

