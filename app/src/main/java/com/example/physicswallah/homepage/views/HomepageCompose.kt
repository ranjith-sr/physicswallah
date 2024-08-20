package com.example.physicswallah.homepage.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.physicswallah.R
import com.example.physicswallah.homepage.repository.datemodel.Character
import com.example.physicswallah.ui.theme.bottomBarTextColor

@Composable
fun HomepageView(
    characterList: List<Character>,
    onItemClick: (Character) -> Unit,
    pageNavigation: (PageNavigation) -> Unit,
    preButton: String?,
    nextButton: String?
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 50.dp)
            ) {
                TopBarText(content = context.getString(R.string.prev),
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        pageNavigation(PageNavigation.PREV)
                    }, if (preButton == null) Color.Gray else bottomBarTextColor)
                TopBarText(content = context.getString(R.string.next),
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        pageNavigation(PageNavigation.NEXT)
                    }, color = if (nextButton == null) Color.Gray else bottomBarTextColor)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(characterList) { character ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally ,
                    modifier = Modifier
                        .clickable {
                            onItemClick(character)
                        }
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = "Image",
                        modifier = Modifier
                            .height(150.dp)
                    )
                    Text(
                        text = character.name ,
                        modifier = Modifier
                            .padding(vertical = 5.dp) ,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun TopBarText(content : String , modifier: Modifier , color : Color) {
    Text(
        text = content,
        fontWeight = FontWeight.ExtraBold,
        modifier = modifier,
        textAlign = TextAlign.Center,
        color = color
    )
}

enum class PageNavigation {
    PREV , NEXT
}