package com.example.physicswallah.homepage.views

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.physicswallah.R
import com.example.physicswallah.homepage.repository.datemodel.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(character: Character , onBack : ()-> Unit){
    val layoutConfig = LocalConfiguration.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "BackIcon",
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                onBack()
                            }
                            .padding(10.dp)
                    )
                }
            )
        }
    ) { padding ->
        val modifier = Modifier.fillMaxSize().padding(padding)
        if(layoutConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Row(modifier = modifier.padding(horizontal = 40.dp)) {
                CharacterImage(url = character.image)
                CharacterInfo(character = character)
            }
        } else {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CharacterImage(url = character.image)
                CharacterInfo(character = character)
            }
        }
    }
}

@Composable
fun CharacterImage(url : String) {
    AsyncImage(
        model = url,
        contentDescription = "Image",
        modifier = Modifier
            .height(150.dp)
    )
}

@Composable
fun CharacterInfo(character: Character) {
    val current = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextComposable(content = "${current.getString(R.string.id)}  :  ${character.id}")
        CustomTextComposable(content = "${current.getString(R.string.name)}  :  ${character.name}")
        CustomTextComposable(content = "${current.getString(R.string.species)}  :  ${character.species}")
        CustomTextComposable(content = "${current.getString(R.string.status)}  :  ${character.status}")
        CustomTextComposable(content = "${current.getString(R.string.gender)}  :  ${character.gender}")
        CustomTextComposable(content = "${current.getString(R.string.origin)}  :  ${character.origin.name}")
        CustomTextComposable(content = "${current.getString(R.string.location)}  :  ${character.location.name}")
        CustomTextComposable(content = "${current.getString(R.string.episodes_count)}  :  ${character.episode.size}")
        CustomTextComposable(content = "${current.getString(R.string.created)}  :  ${character.created}")
    }
}


@Composable
fun CustomTextComposable(content : String) {
    Text(
        text = content ,
        modifier = Modifier
            .padding(vertical = 5.dp) ,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}