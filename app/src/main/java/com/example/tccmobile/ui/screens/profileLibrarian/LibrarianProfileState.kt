package com.example.tccmobile.ui.screens.profileLibrarian

data class LibrarianProfileState(
    val name: String = "",
    val initial: String ="",
    val role: String = "Administrador", // Cargo ou Função
    val email: String = "",
    val registry: String = "", // Opcional
    val received: Int = 0,

    val isLoadingLogout: Boolean = false,
    val isLoadingData: Boolean = false,
    val errorMessage: String? = null
)
