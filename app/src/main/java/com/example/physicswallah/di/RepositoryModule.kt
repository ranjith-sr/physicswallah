package com.example.physicswallah.di

import com.example.physicswallah.homepage.repository.CharacterApi
import com.example.physicswallah.homepage.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun createRetrofit() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getCharacterApi( retrofit: Retrofit) : CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }

    @Provides
    fun getRepository(characterApi: CharacterApi) : Repository {
        return Repository(characterApi)
    }
}