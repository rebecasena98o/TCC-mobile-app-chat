package com.example.tccmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// IMPORTS NECESSÁRIOS: Garante que os componentes e dados de outros arquivos sejam encontrados
import com.example.tccmobile.R // Para acessar R.drawable.xxx (ícones)
import com.example.tccmobile.ui.components.DashboardBibliotecario.CompletionCardData
import com.example.tccmobile.ui.components.DashboardBibliotecario.DashboardHeader
import com.example.tccmobile.ui.components.DashboardBibliotecario.MetricCardRow
import com.example.tccmobile.ui.theme.AmareloClaro1
import com.example.tccmobile.ui.theme.IconBackgroundGreen
import com.example.tccmobile.ui.theme.IconBackgroundRed
import com.example.tccmobile.ui.theme.IconBackgroundYellow
import com.example.tccmobile.ui.theme.Pink40
import com.example.tccmobile.ui.theme.Pink80
import com.example.tccmobile.ui.theme.VermelhoTelha
// Importando as cores necessárias
import com.example.tccmobile.ui.theme.black
import com.example.tccmobile.ui.theme.white
import com.example.tccmobile.ui.theme.green
import com.example.tccmobile.ui.theme.yellow


@Composable
fun DashboardScreen() {

    // DADOS DE TESTE (MOCK) - Lista de objetos CompletionCardData para preencher a tela.
    val sampleMetrics = listOf(
        CompletionCardData(
            mainTitle = "TCCs Corrigidos",
            detailText = "15 aprovados com sucesso",
            // USANDO Material Icons (CheckCircle)
            iconVector = Icons.Default.Assignment,
            iconTint = green,
            iconBackgroundColor = IconBackgroundGreen
        ),
        CompletionCardData(
            mainTitle = "Pendentes",
            detailText = "Em Análise",
            // USANDO Material Icons (PendingActions)
            iconVector = Icons.Default.AccessTime,
            iconTint = AmareloClaro1,
            iconBackgroundColor = IconBackgroundYellow // NOVO: Fundo

        ),
        CompletionCardData(
            mainTitle = "Concluídos",
            detailText = "aprovados com sucesso",
            // USANDO Material Icons (Assignment)
            iconVector = Icons.Default.CheckCircleOutline,
            iconTint = VermelhoTelha,
            iconBackgroundColor = IconBackgroundRed // NOVO: Fundo

        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white) // Fundo da tela
            .verticalScroll(rememberScrollState()) // Permite rolagem
    ) {
        // 1. HEADER (Componente de cabeçalho)
        DashboardHeader(onBackClicked = {
            println("Navegar de volta...")
        })

        // 2. MEUS INDICADORES / LINHA DE CARDS
        // Chamada do componente MetricCardRow com os dados mockados
        MetricCardRow(cardMetrics = sampleMetrics)


    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}