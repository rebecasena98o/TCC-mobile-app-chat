package com.example.tccmobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tccmobile.ui.screens.chatStudent.ChatStudentScreen
import com.example.tccmobile.ui.screens.loginScreen.LoginScreen
import com.example.tccmobile.ui.screens.registerScreen.RegisterScreen
import com.example.tccmobile.ui.screens.newTicketScreen.NewTicketScreen
import com.example.tccmobile.data.supabase.SupabaseClient.client
import io.github.jan.supabase.auth.auth

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Routes.REGISTER)
                },
                onLoginSuccess = {
                    navController.navigate(Routes.ticket("123")) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.REGISTER) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onRegisterSuccess = {
                    navController.popBackStack()
                }
            )
        }

        //Precisa ser implementado sua rota correta quando o dashboard for criado
        composable(Routes.NEW_TICKET){
            NewTicketScreen(
                onBackClick = {

                    navController.popBackStack()
                },
                onTicketCreated = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.TICKET,
            arguments = listOf(
                navArgument("id"){ type = NavType.StringType }
            ))
        { entry ->
            val id = entry.arguments?.getString("id")


            val session = client.auth.currentSessionOrNull()
            val isStudent = session?.user?.userMetadata?.get("isStudent")


            if(!id.isNullOrEmpty() && isStudent != null){
                ChatStudentScreen(
                    ticketId = id,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}