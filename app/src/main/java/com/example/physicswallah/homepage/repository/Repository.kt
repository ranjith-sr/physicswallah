package com.example.physicswallah.homepage.repository

import com.example.physicswallah.homepage.repository.datemodel.Character
import com.example.physicswallah.homepage.repository.datemodel.CharacterInfo
import retrofit2.Response

class Repository(var characterApi: CharacterApi) {
    suspend fun getCharacter(id : Int) : NetworkResult<Character>{
        return handleNetwork {
            characterApi.getCharacterInfo(id)
        }
    }

    suspend fun getCharacterList() : NetworkResult<CharacterInfo>{
        return handleNetwork {
            characterApi.getCharacterList()
        }
    }

    suspend fun getCharacterList(url : String) : NetworkResult<CharacterInfo>{
        return handleNetwork {
            characterApi.getCharacterList(url)
        }
    }

    private suspend fun <T : Any> handleNetwork(execute : suspend ()-> Response<T>) : NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if(response.isSuccessful && body != null){
                NetworkResult.Success(body)
            } else {
                NetworkResult.Failed(400 , "Unknown Error") // For Temporary
            }
        } catch (e : Exception){
            NetworkResult.Error(e)
        }
    }
}
