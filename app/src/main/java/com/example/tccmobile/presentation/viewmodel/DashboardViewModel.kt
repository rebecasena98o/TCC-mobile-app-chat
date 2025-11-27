package com.example.tccmobile.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tccmobile.features.dashboard.data.models.Metric
import com.example.tccmobile.features.dashboard.data.models.QualityMetric
import com.example.tccmobile.features.dashboard.data.models.ResponseTimeMetric
import com.example.tccmobile.features.dashboard.data.models.Review
import com.example.tccmobile.features.dashboard.data.models.TeamMember
import com.example.tccmobile.features.dashboard.data.DashboardRepositoryMock
import com.example.tccmobile.features.dashboard.data.models.*
import com.example.tccmobile.features.dashboard.domain.repository.DashboardRepository
import kotlinx.coroutines.launch

// Estado que a UI irá observar
data class DashboardUiState(
    val isLoading: Boolean = true,
    val myMetrics: List<Metric> = emptyList(),
    val qualityMetric: QualityMetric? = null,
    val responseTimeMetric: ResponseTimeMetric? = null,
    val recentReviews: List<Review> = emptyList(),
    val teamPerformance: List<TeamMember> = emptyList(),
    val errorMessage: String? = null
)

// Injeta o repositório como dependência
class DashboardViewModel(
    private val repository: DashboardRepository = DashboardRepositoryMock() // Usando o mock por enquanto
) : ViewModel() {

    // Estado interno que será exposto para a tela
    private val _uiState = mutableStateOf(DashboardUiState())
    val uiState: State<DashboardUiState> = _uiState

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        // Inicia o estado de loading
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            try {
                // Chama o método que busca todos os dados do Repositório
                val dataBundle = repository.getAllDashboardData()

                // Atualiza o estado da UI com os dados carregados
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    myMetrics = dataBundle.myMetrics,
                    qualityMetric = dataBundle.qualityMetric,
                    responseTimeMetric = dataBundle.responseTimeMetric,
                    recentReviews = dataBundle.recentReviews,
                    teamPerformance = dataBundle.teamPerformance
                )

            } catch (e: Exception) {
                // Em caso de erro
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Erro ao carregar o dashboard: ${e.localizedMessage}"
                )
            }
        }
    }
}