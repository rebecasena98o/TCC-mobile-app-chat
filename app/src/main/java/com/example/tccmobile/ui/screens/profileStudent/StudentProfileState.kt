package com.example.tccmobile.ui.screens.profileStudent

data class StudentProfileState(
    val name: String = "",
    val initial: String ="",
    val email: String = "",
    val registry: String = "",
    val course: String = "",
    val sent: Int = 0,
    val inAnalysis: Int = 0,

    val isLoadingLogout: Boolean = false,
    val isLoadingData: Boolean = false,
    val errorMessage: String? = null
)
