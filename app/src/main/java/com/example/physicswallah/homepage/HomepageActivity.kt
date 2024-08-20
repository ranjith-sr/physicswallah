package com.example.physicswallah.homepage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.physicswallah.homepage.viewmodel.HomepageViewModel
import com.example.physicswallah.homepage.views.HomePageNavHost
import com.example.physicswallah.ui.theme.PhysicsWallahTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomepageActivity : ComponentActivity() {
    val viewModel : HomepageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            PhysicsWallahTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedEffect(key1 = Unit) {
                        viewModel.toastMessage.collect{ message ->
                            Toast.makeText(context , message , Toast.LENGTH_LONG ).show()
                        }
                    }

                    HomePageNavHost(
                        viewModel.characterList, {
                            viewModel.onPageNavigation(it)
                        },
                        viewModel.currCharacter, {
                            viewModel.currCharacter = it
                        }, viewModel.prev, viewModel.next
                    )
                }
            }
        }
    }
}