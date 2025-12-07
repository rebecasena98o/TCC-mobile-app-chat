package com.example.tccmobile.ui.screens.profileLibrarian

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tccmobile.data.repository.AuthRepository
import com.example.tccmobile.data.repository.TicketRepository
import com.example.tccmobile.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.joinToString
import kotlin.text.first

class LibrarianProfileViewModel(
    val userRepository: UserRepository = UserRepository(),
    val authRepository: AuthRepository = AuthRepository(),
    val ticketRepository: TicketRepository = TicketRepository()
): ViewModel()  {
    private val _uiState = MutableStateFlow(LibrarianProfileState())
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

    private fun setInfo(name: String, email: String, registry: String, received: Int){
        _uiState.update {
            val initial = name.split(" ").take(2).map { it -> it.first() }.joinToString("")
            it.copy(name = name, initial = initial, email = email, registry = registry, received = received)
        }
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            setIsLoadingData(true)
            val auth = authRepository.getUserInfo()

            auth?.id?.let { id ->
                val userInfo = userRepository.getUserInfo(id)
                val ticket = ticketRepository.getAllTicket()

                if(userInfo == null){
                    setIsLoadingData(false)
                    setError("Erro ao buscar os dados do usuario")
                    return@launch
                }

                setInfo(
                    name = userInfo.name,
                    email = userInfo.email,
                    registry = userInfo.registry,
                    received = ticket.size
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