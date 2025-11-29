package com.example.tccmobile.ui.screens.chatStudent

import android.net.Uri

data class ChatStudentState(
    val theme: String = "",
    val course: String = "",
    val status: String = "",


    val message: String = "",

    val attachmentUri: Uri? = null,
    val attachmentFileName: String = "",


    val isLoading: Boolean = false,

    val chatError: String? = null
    )
