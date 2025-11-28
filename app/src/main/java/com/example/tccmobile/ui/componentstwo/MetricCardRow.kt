package com.example.tccmobile.ui.componentstwo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tccmobile.ui.theme.Pink40
import com.example.tccmobile.ui.theme.Purple80

import com.example.tccmobile.ui.theme.PurpleGrey40
import com.example.tccmobile.ui.theme.TextPrimary
import com.example.tccmobile.ui.theme.black
import com.example.tccmobile.ui.theme.white
import com.example.tccmobile.ui.theme.yellow

// Modelo de dados simples para uma métrica (simplificado)
data class MetricData(
    val icon: ImageVector,
    val value: String,
    val label: String,
    val iconColor: Color
)

@Composable
fun MetricCard(data: MetricData) {
    Card(
        shape = RoundedCornerShape(11.dp),
        // Removido o fillMaxWidth e height fixo, o Column abaixo vai gerenciar o tamanho
        modifier = Modifier
            .fillMaxWidth() // O card ocupa 100% da largura do seu pai (o Column abaixo)
            .background(white, RoundedCornerShape(12.dp))
    ) {
        Row( // Mudamos para Row aqui dentro para ter Icone e Texto em linha se necessário, mas mantive a estrutura de Column para o conteúdo ser como antes
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(white), // Garante que o fundo do Card é CardBackground
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Coluna para Icone e Textos
            Column(
                modifier = Modifier.weight(30f) // Ocupa o restante do espaço
            ) {
                // APLICANDO A COR DO ÍCONE
                Icon(
                    imageVector = data.icon,
                    contentDescription = data.label,
                    tint = data.iconColor, // Usando a cor definida no MetricData
                    modifier = Modifier.size(28.dp) // Aumentei um pouco o tamanho do ícone
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = data.value,
                    color = black,
                    fontSize = 20.sp, // Aumentei a fonte para destaque
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = data.label,
                    color = black.copy(alpha = 0.6f),
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun MetricCardRow() {
    // Definindo as métricas com as cores independentes
    val metrics = listOf(
        MetricData(
            icon = Icons.Default.Star,
            value = "8.5/10",
            label = "Qualidade Média",
            iconColor = Purple80 // Verde
        ),
        MetricData(
            icon = Icons.Default.List,
            value = "120",
            label = "Revisões Totais",
            iconColor = yellow // Amarelo
        ),
        MetricData(
            icon = Icons.Default.Info,
            value = "95%",
            label = "Índice de Conformidade",
            iconColor = Pink40  // Vermelho
        )
    )

    // CORREÇÃO: Usamos Column para que os cards apareçam um abaixo do outro
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espaçamento entre os cards
    ) {
        // Usa o ForEach para empilhar os cards
        metrics.forEach { metric ->
            MetricCard(data = metric)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MetricCardRowPreview() {
    MetricCardRow()
}