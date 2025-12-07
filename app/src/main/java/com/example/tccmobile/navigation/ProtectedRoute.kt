package com.example.tccmobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.github.jan.supabase.auth.user.UserSession

@Composable
fun ProtectedRoute(
    session: UserSession?,
    onAuthFailed: () -> Unit,
    content: @Composable (session: UserSession) -> Unit
) {
    LaunchedEffect (session) {
        if (session == null) onAuthFailed()
    }

    if (session != null) {
        content(session)
    }
}