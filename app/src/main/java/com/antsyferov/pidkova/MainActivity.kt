package com.antsyferov.pidkova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.antsyferov.main.PidkovaApp

class MainActivity : ComponentActivity() {

    /*private val anotherInterface: AnotherInterface by inject(named("impl2"))
    private val someInterface: SomeInterface by inject()

    private val useCase: UseCase by inject()
    private val remoteApi: RemoteApiImpl by inject()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PidkovaApp()
        }
    }
}
