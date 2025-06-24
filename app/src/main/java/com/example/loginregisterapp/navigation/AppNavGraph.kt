package com.example.loginregisterapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginregisterapp.presentation.login.LoginScreen
import com.example.loginregisterapp.presentation.register.RegisterScreen
import com.example.loginregisterapp.presentation.success.SuccessScreen

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val SUCCESS = "success"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }
        composable(Routes.REGISTER) {
            RegisterScreen(navController)
        }
        composable(Routes.SUCCESS) {
            SuccessScreen(navController)
        }
    }
}
