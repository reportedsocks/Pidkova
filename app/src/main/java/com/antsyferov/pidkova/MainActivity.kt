package com.antsyferov.pidkova

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antsyferov.domain.UseCase
import com.antsyferov.network.RemoteApiImpl
import com.antsyferov.pidkova.home.HomeEffects
import com.antsyferov.pidkova.home.HomeEvents
import com.antsyferov.pidkova.home.HomeViewModel
import com.antsyferov.ui.theme.PidkovaTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

class MainActivity : ComponentActivity() {

    private val anotherInterface: AnotherInterface by inject(named("impl2"))
    private val someInterface: SomeInterface by inject()

    private val useCase: UseCase by inject()
    private val remoteApi: RemoteApiImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d("MyLogs", anotherInterface.javaClass.name)
        Log.d("MyLogs", someInterface.javaClass.name)
        Log.d("MyLogs", useCase.javaClass.name)
        setContent {
            KoinAndroidContext {
                PidkovaTheme {

                    val viewModel = koinViewModel<HomeViewModel>()

                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val effects = rememberFlowWithLifecycle(viewModel.effect)

                    val context = LocalContext.current

                    LaunchedEffect(effects) {
                        effects.collect {
                            when(it) {
                                is HomeEffects.ShowToast -> {
                                    Toast.makeText(context, "Loaded products", Toast.LENGTH_SHORT).show()
                                }
                                else -> {}
                            }
                        }
                    }

                    val coroutineScope = rememberCoroutineScope()

                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            Greeting(
                                name = state.products.toString().take(20)
                            )

                            Button(
                                onClick = {
                                    viewModel.sendEvent(HomeEvents.LoadProducts)
                                }
                            ) {
                                Text("load products")
                            }

                            Button(
                                onClick = {
                                    coroutineScope.launch(Dispatchers.IO) {
                                        val response = remoteApi.getProfile()
                                        Log.d("MyLogs", response.toString())
                                    }

                                }
                            ) {
                                Text("profile")
                            }

                            Button(
                                onClick = {
                                    coroutineScope.launch(Dispatchers.IO) {
                                        val response = remoteApi.auth()
                                    }

                                }
                            ) {
                                Text("auth")
                            }

                            Button(
                                onClick = {
                                    coroutineScope.launch(Dispatchers.IO) {
                                        val response = remoteApi.openSocket()
                                    }

                                }
                            ) {
                                Text("Open Socket")
                            }

                            Button(
                                onClick = {
                                    coroutineScope.launch(Dispatchers.IO) {
                                        val response = remoteApi.sendMessage()
                                    }

                                }
                            ) {
                                Text("send message")
                            }
                        }


                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PidkovaTheme {
        Greeting("Android")
    }
}