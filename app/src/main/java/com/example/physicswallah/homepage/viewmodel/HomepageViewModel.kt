package com.example.physicswallah.homepage.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.physicswallah.homepage.repository.NetworkResult
import com.example.physicswallah.homepage.repository.Repository
import com.example.physicswallah.homepage.repository.datemodel.Character
import com.example.physicswallah.homepage.repository.datemodel.CharacterInfo
import com.example.physicswallah.homepage.views.PageNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(var repository: Repository) : ViewModel() {

    var characterList = mutableStateListOf<Character>()
    var currCharacter by mutableStateOf<Character?>(null)
    private var _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()
    var prev by mutableStateOf<String?>(null)
    var next by mutableStateOf<String?>(null)

    init {
        getCharacterList()
    }

    fun getCharacterList() {
        viewModelScope.launch {
            when ( val res = repository.getCharacterList()) {
                is NetworkResult.Success -> {
                    updateCharacterInfo(res.data)
                }

                is NetworkResult.Failed -> {

                }

                is NetworkResult.Error -> {
                    handleException(res.throwable)
                }
            }
        }
    }

    fun onPageNavigation(pageNavigation: PageNavigation) {
        viewModelScope.launch {
            val url = if(pageNavigation == PageNavigation.PREV) prev else next
            if(url == null) return@launch
            when ( val res = repository.getCharacterList( url )) {
                is NetworkResult.Success -> {
                    updateCharacterInfo(res.data)
                }

                is NetworkResult.Failed -> {

                }
                is NetworkResult.Error -> {
                    handleException(res.throwable)
                }
            }
        }
    }

    suspend fun handleException(throwable: Throwable){
        when(throwable){
            is UnknownHostException -> {
                _toastMessage.emit("No Network Connection")
            }
        }
    }

    private fun updateCharacterInfo(characterInfo: CharacterInfo) {
        characterInfo.results?.let { characterList ->
            this.characterList.clear()
            this.characterList.addAll(characterList)
        }
        prev = characterInfo.info.prev
        next = characterInfo.info.next
    }
}