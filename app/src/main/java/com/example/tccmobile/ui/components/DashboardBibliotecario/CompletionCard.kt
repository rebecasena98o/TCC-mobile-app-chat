package com.example.tccmobile.ui.components.DashboardBibliotecario

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.foundation.background
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// MODELO DE DADOS CENTRALIZADO: Esta é a única definição para evitar conflitos.
data class CompletionCardData(
    val mainTitle: String, // Título principal (ex: "15")
    val detailText: String, // Texto de detalhe (ex: "TCCs Corrigidos")
    val iconVector: ImageVector,
    val iconTint: Color,
    val iconBackgroundColor: Color
)


/**
 * Componente que exibe um único Card de Métrica/Conclusão com ícone e texto.
 */
@Composable
fun CompletionCard(
    data: CompletionCardData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(56.dp) // Tamanho maior para o Box de fundo
                    .background(
                        data.iconBackgroundColor,
                        RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                // Lado Esquerdo: Ícone
                Icon(
                    imageVector = data.iconVector, // Usando o ImageVector
                    contentDescription = "Ícone de ${data.mainTitle}",
                    tint = data.iconTint,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 1.dp)
                )
            }

                // Espaçamento entre o Ícone e o Texto
                Spacer(Modifier.width(16.dp))

                // 2. Lado Direito: Textos (Ocupa o espaço restante e se alinha à direita)
                // ESTE É O IRMÃO DIRETO DO Box DENTRO DO ROW, AGORA FUNCIONA!
                Column(
                    modifier = Modifier.weight(
                        1F,
                        fill = true
                    ), // CORRIGIDO! Agora o weight é usado corretamente pelo Row
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                )
                {
                    // Título Principal (Ex: "15")
                    Text(
                        text = data.mainTitle,
                        color = Color.Black,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )

                    Spacer(Modifier.height(4.dp))

                    // Texto de Detalhe (Ex: "TCCs Corrigidos")
                    Text(
                        text = data.detailText,
                        color = Color.Black.copy(alpha = 0.6f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }
            }
        }
    }
