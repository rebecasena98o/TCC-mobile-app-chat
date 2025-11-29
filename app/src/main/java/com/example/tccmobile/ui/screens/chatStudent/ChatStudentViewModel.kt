package com.example.tccmobile.ui.screens.chatStudent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatStudentViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChatStudentState())
    val uiState = _uiState.asStateFlow()

    private fun setTheme(v: String) {
        _uiState.update { it.copy(theme = v) }
    }

    private fun setCourse(v: String){
        _uiState.update {  it.copy(course = v) }
    }

    fun setStatus(v: String){
        _uiState.update { it.copy(status = v) }
    }

    fun getTheme(): String{
        return _uiState.value.theme
    }

    fun getCourse(): String{
        return _uiState.value.course
    }

    fun getStatus(): String{
        return _uiState.value.status
    }

    fun fetchTicket(ticketId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

        }
    }
}