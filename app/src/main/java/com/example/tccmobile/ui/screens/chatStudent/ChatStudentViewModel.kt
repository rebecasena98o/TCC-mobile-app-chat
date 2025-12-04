package com.example.tccmobile.ui.screens.chatStudent

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tccmobile.data.entity.Message
import com.example.tccmobile.data.repository.AuthRepository
import com.example.tccmobile.data.repository.MessageRepository
import com.example.tccmobile.data.repository.TicketRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class ChatStudentViewModel(
    private val messageRepository: MessageRepository = MessageRepository(),
    private val authRepository: AuthRepository = AuthRepository(),
    private val ticketRepository: TicketRepository = TicketRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(ChatStudentState())
    val uiState = _uiState.asStateFlow()

    fun init(channelId: Int){
        viewModelScope.launch {
            messageRepository.startListening(channelId){
                insertNewMessage(it)
            }
        }
    }

    override fun onCleared() {
        viewModelScope.launch {
            super.onCleared()
            messageRepository.clear()
        }

    }

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
    fun sendMessage(ticketId: Int){
        viewModelScope.launch {
            val userId = authRepository.getUserInfo()?.id
            if(_uiState.value.inputMessage.isEmpty() && userId == null) return@launch

            messageRepository.sendMessage(
                content = _uiState.value.inputMessage,
                ticketId = ticketId,
                senderId = userId as String,
            )

            setInputMessage(" ")
        }
    }

    private fun insertNewMessage(id: Int){
        viewModelScope.launch {
            val newMessage = messageRepository.getNewMessage(id)
            if(newMessage != null)
                setMessagesList(listOf(newMessage))
        }
    }

    @OptIn(ExperimentalTime::class)
    fun fetchTicket(ticketId: Int) {
        viewModelScope.launch {
            setIsLoanding(true)

            val ticket = ticketRepository.getTicket(ticketId)

            if(ticket == null) return@launch

            setTheme(ticket.subject)
            setCourse(ticket.course)
            setStatus(ticket.status)

            setMessagesList(messageRepository.listMessages(ticketId))

            setIsLoanding(false)
        }
    }
}