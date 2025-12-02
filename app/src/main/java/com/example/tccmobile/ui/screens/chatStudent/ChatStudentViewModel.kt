package com.example.tccmobile.ui.screens.chatStudent

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tccmobile.data.entity.Message
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class ChatStudentViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(ChatStudentState())
    val uiState = _uiState.asStateFlow()

    private fun setTheme(v: String) {
        _uiState.update { it.copy(theme = v) }
    }

    private fun setCourse(v: String){
        _uiState.update {  it.copy(course = v) }
    }

    private fun setIsLoanding(v: Boolean){
        _uiState.update { it.copy(isLoading = v) }
    }

    fun setInputMessage(message: String){
        _uiState.update {
            it.copy(inputMessage = message)
        }
    }

    private fun setMessagesList(messages: List<Message>){
        _uiState.update {
            it.copy( messages = it.messages + messages)
        }
    }

    fun setStatus(v: String){
        _uiState.update { it.copy(status = v) }
    }

    @OptIn(ExperimentalTime::class)
    fun sendMessage(){
        if(_uiState.value.inputMessage.isEmpty()) return

        setMessagesList(listOf(Message(
            id = 3,
            content = _uiState.value.inputMessage,
            senderName = "teste",
            ticketId = "123",
            isStudent = true,
            createdAt = Instant.parse("2025-11-29T04:07:25.365188+00")
        )))

        setInputMessage(" ")
    }

    @OptIn(ExperimentalTime::class)
    fun fetchTicket(ticketId: String) {
        viewModelScope.launch {
            setIsLoanding(true)


            setTheme("Teste")
            setCourse("curso teste")
            setStatus("Aberto")

            setMessagesList(listOf(
                Message(
                    id = 1,
                    content = "testando",
                    senderName = "joao",
                    ticketId = "123",
                    isStudent = true,
                    createdAt = Instant.parse("2025-11-29T04:07:25.365188+00"),
                    fileName = "teste-1",
                    fileSize = 11000,
                    fileType = "docx",
                ),
                Message(
                    id = 2,
                    content = "testando",
                    senderName = "joao",
                    ticketId = "123",
                    isStudent = false,
                    createdAt = Instant.parse("2025-11-29T04:07:25.365188+00"),
                )
            ))

            delay(1500)
            setIsLoanding(false)
        }
    }
}