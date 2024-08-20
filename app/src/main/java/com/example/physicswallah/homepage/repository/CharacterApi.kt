package com.example.physicswallah.homepage.repository

import com.example.physicswallah.homepage.repository.datemodel.Character
import com.example.physicswallah.homepage.repository.datemodel.CharacterInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface CharacterApi {
    @GET("/api/character")
    suspend fun getCharacterList() : Response<CharacterInfo>

    @GET("/api/character/{id}")
    suspend fun getCharacterInfo(@Path("id") id : Int) : Response<Character>

    @GET
    suspend fun getCharacterList(@Url url : String) : Response<CharacterInfo>
}