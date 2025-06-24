package com.example.loginregisterapp.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.loginregisterapp.navigation.Routes
import com.example.loginregisterapp.presentation.AuthViewModel
import com.example.loginregisterapp.util.Resource

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val result by viewModel.authResult.collectAsState()

    LaunchedEffect(result) {
        when (val res = result) {
            is Resource.Loading -> {
                isLoading = true
            }
            is Resource.Success -> {
                isLoading = false
                navController.navigate(Routes.LOGIN)
                viewModel.clearResult()
            }
            is Resource.Error -> {
                isLoading = false
                errorMessage = (result as Resource.Error<String>).message ?: "An unknown error occurred"
                showDialog = true
                viewModel.clearResult()
            }
            null -> {}
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Register", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                if (username.isBlank() || password.isBlank()) {
                    errorMessage = "Please enter username and password"
                    showDialog = true
                } else {
                    isLoading = true
                    viewModel.register(username, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            enabled = !isLoading
        ) {
            Text("Register")
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )
        }

        TextButton(onClick = { navController.navigate(Routes.LOGIN) }) {
            Text("Already have an account? Login")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Registration Error") },
                text = { Text(errorMessage) }
            )
        }
    }
}
