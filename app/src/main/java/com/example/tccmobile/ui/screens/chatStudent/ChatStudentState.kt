package com.example.tccmobile.ui.screens.chatStudent

import android.net.Uri
import com.example.tccmobile.data.entity.Message

data class ChatStudentState(
    val theme: String = "",
    val course: String = "",
    val status: String = "",

    val inputMessage: String = "",

    val messages: List<Message> = listOf(),

    val isLoading: Boolean = false,
    val chatError: String? = null
)
