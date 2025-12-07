package com.example.tccmobile.ui.screens.profileStudent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tccmobile.data.repository.AuthRepository
import com.example.tccmobile.data.repository.TicketRepository
import com.example.tccmobile.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.Locale.getDefault
import kotlin.collections.joinToString
import kotlin.text.first

class StudentProfileViewModel(
    val userRepository: UserRepository = UserRepository(),
    val authRepository: AuthRepository = AuthRepository(),
    val ticketRepository: TicketRepository = TicketRepository()
): ViewModel()  {
    private val _uiState = MutableStateFlow(StudentProfileState())
    val uiState = _uiState.asStateFlow()

    init {
        // Altere para UserType.LIBRARIAN para testar o perfil da bibliotecária
        loadUserProfile()
    }

    private fun setIsLoadingData(v: Boolean){
        _uiState.update {
            it.copy(isLoadingData = v)
        }
    }

    private fun setError(v: String){
        _uiState.update {
            it.copy(errorMessage = v)
        }
    }

    private fun setInfo(name: String, email: String, course: String, registry: String, sent: Int, inAnalysis: Int){
        _uiState.update {
            val initial = name.split(" ").take(2).map { it -> it.first() }.joinToString("")
            it.copy(name = name, initial = initial, course= course, email = email, registry = registry, sent = sent, inAnalysis = inAnalysis)
        }
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            setIsLoadingData(true)
            val auth = authRepository.getUserInfo()

            auth?.id?.let { id ->
                val studentInfo = userRepository.getStudentById(id)
                val ticket = ticketRepository.getAllTicketByStudent(id)
                val countPending = ticket.filter { it.status.label.lowercase(getDefault()) == "pendente" }.size

                if(studentInfo == null){
                    setIsLoadingData(false)
                    setError("Erro ao buscar os dados do usuario")
                    return@launch
                }

                Log.d("SUPABASE_LOG", ticket.toString())

                setInfo(
                    name = studentInfo.name,
                    email = studentInfo.email,
                    registry = studentInfo.registry,
                    course = studentInfo.course,
                    sent = ticket.size,
                    inAnalysis = countPending
                )

                setIsLoadingData(false)
            }
        }
    }

    fun onLogoutClick(onLogoutSuccess: () -> Unit) {
        viewModelScope.launch {
            // Inicia o estado de carregamento de logout
            _uiState.update { it.copy(isLoadingLogout = true) }

            authRepository.signOut()

            _uiState.update { it.copy(isLoadingLogout = false) }
            onLogoutSuccess()
        }
    }
}