package com.cursokotlin.uptprpyecto.chatgtp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cursokotlin.uptprpyecto.chatgtp.viewmodel.ChatGTPViewModel


//@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
//@Preview(name = "pixel4", device = "id:pixel_4")
//@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun ChatScreen(chatGtpViewModel: ChatGTPViewModel) {
    val isLoading :Boolean by chatGtpViewModel.isLoading.observeAsState(initial = false)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (isLoading){
            Box(modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }else {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                BodyScreen(Modifier.weight(9f, true), chatGtpViewModel)
                Spacer(modifier = Modifier.size(15.dp))
                FooterScreen(Modifier.weight(1f, true), chatGtpViewModel)
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FooterScreen(modifier: Modifier, chatGtpViewModel: ChatGTPViewModel) {

    val pregunta:String by chatGtpViewModel.pregunta.observeAsState(initial = "")
    val isBotonEnable: Boolean by chatGtpViewModel.isBotonEnabled.observeAsState(initial = false)



    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
    ) {
        Row(Modifier.fillMaxSize(),verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = pregunta,
                onValueChange = { chatGtpViewModel.onPreguntaChanged(it)  },
                modifier = Modifier.weight(4f),
                placeholder = { Text(text = "Preguntame ...") },
                maxLines = 1,
                singleLine = true,
                trailingIcon = {
                    if (pregunta.isNotEmpty())
                        IconButton(onClick = {  chatGtpViewModel.onPreguntaChanged("") }) {
                            Icon(imageVector = Icons.Filled.Close, contentDescription = "show password")
                        }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xFFB2B2B2),
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            IconButton(onClick = { chatGtpViewModel.onPreguntarToChatGTP() }, modifier = Modifier.weight(1f), enabled = isBotonEnable) {
                Icon(
                    imageVector = Icons.Default.ArrowUpward,
                    contentDescription = "")
            }



        }
    }
}

@Composable
fun BodyScreen(modifier: Modifier, chatGtpViewModel: ChatGTPViewModel) {
    val respuestaChat :String by chatGtpViewModel.respuestaChat.observeAsState(initial = "")

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    ) {
        val scroll = rememberScrollState(0)
        Text(text = respuestaChat,
            Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .verticalScroll(scroll))

    }
}


