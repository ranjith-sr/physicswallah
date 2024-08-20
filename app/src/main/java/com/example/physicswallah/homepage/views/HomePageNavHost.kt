package com.example.physicswallah.homepage.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.physicswallah.homepage.repository.datemodel.Character

@Composable
fun HomePageNavHost(
    characterList: List<Character>,
    pageNavigation: (PageNavigation) -> Unit,
    currCharacter: Character?,
    updateCurrCharacter: (Character) -> Unit,
    preButton : String?,
    nextButton: String?
) {
    val navController = rememberNavController();
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomepageView(characterList = characterList, {
                updateCurrCharacter(it)
                navController.navigate("details")
            }, {
                pageNavigation(it)
            }, preButton, nextButton)
        }
        composable("details") {
            currCharacter?.let { character ->
                DetailView(character = currCharacter) {
                    navController.popBackStack()
                }
            }
        }
    }
}