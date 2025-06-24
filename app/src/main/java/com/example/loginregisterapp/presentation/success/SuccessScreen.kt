package com.example.loginregisterapp.presentation.success

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginregisterapp.navigation.Routes

@Composable
fun SuccessScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to My App...!", style = MaterialTheme.typography.headlineLarge)

        Button(
            onClick = {
                navController.navigate(Routes.LOGIN)
            },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text("Logout")
        }
    }
}
