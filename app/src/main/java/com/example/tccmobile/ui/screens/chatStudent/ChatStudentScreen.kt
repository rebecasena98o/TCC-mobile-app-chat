package com.example.tccmobile.ui.screens.chatStudent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tccmobile.ui.components.chat.ChatInputBar
import com.example.tccmobile.ui.components.chat.HeaderStudentChat
import com.example.tccmobile.ui.components.chat.MessageBox
import com.example.tccmobile.ui.components.utils.StatusBadgeModel
import com.example.tccmobile.ui.theme.DarkBlue
import com.example.tccmobile.ui.theme.LightBlue
import com.example.tccmobile.ui.theme.Orange
import com.example.tccmobile.ui.theme.SuperLightOrange
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun ChatStudentScreen(
    viewModel: ChatStudentViewModel = viewModel(),
    ticketId: String,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(ticketId) {
        viewModel.fetchTicket(ticketId)
    }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = LightBlue,
                trackColor = DarkBlue,
            )
        }
    } else {

        Column(
            modifier= Modifier.fillMaxSize()
        ){
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


            LazyColumn(
                modifier = Modifier
                .weight(1f)
                .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                items(items= uiState.messages, key={ it.id }) { message ->
                    MessageBox(
                        message = message.content,
                        nameSubmitter = message.senderName,
                        date = message.createdAt,
                        isStudent = message.isStudent,
                        attachmentName = message.fileName,
                        onAttachmentClick = {

                        }
                    )
                }
            }


            ChatInputBar(
                message = uiState.inputMessage,
                onMessageChange = { viewModel.setInputMessage(it) },
                onSendClick = { viewModel.sendMessage() },
                onAttachClick = {}
            )
        }


    }
}


@Preview
@Composable
fun PreviewChatStudentScreen(){
    ChatStudentScreen(
        ticketId = "123"
    ) { }
}