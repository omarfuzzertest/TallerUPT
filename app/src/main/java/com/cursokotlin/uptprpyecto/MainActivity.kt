package com.cursokotlin.uptprpyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.cursokotlin.uptprpyecto.chatgtp.ui.ChatScreen
import com.cursokotlin.uptprpyecto.chatgtp.viewmodel.ChatGTPViewModel
import com.cursokotlin.uptprpyecto.ui.theme.UPTPrpyectoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val chatGtpViewModel: ChatGTPViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UPTPrpyectoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatScreen(chatGtpViewModel)
                }
            }
        }
    }
}


