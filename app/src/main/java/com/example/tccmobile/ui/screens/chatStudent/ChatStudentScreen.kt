package com.example.tccmobile.ui.screens.chatStudent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tccmobile.ui.components.chat.HeaderStudentChat
import com.example.tccmobile.ui.components.utils.StatusBadgeModel
import com.example.tccmobile.ui.theme.Orange
import com.example.tccmobile.ui.theme.SuperLightOrange

@Composable
fun ChatStudentScreen(
    viewModel: ChatStudentViewModel= viewModel(),
    ticketId: String,
    onBackClick: () -> Unit
){
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(ticketId) {
        viewModel.fetchTicket(ticketId)
    }

    if(uiState.isLoading){
        Text("carregando..")
    }else{
        HeaderStudentChat(
            ticketId = ticketId,
            title = uiState.theme,
            subtitle = uiState.course,
            badges = listOf(
                StatusBadgeModel(
                    text = uiState.status,
                    backgroundColor = SuperLightOrange,
                    textColor = Orange
                )
            ),
            onBackClick = onBackClick
        )
    }


}