package com.example.physicswallah.homepage.repository

sealed class NetworkResult<T : Any> {
    class Success<T : Any>(var data : T) : NetworkResult<T>()
    class Failed<T : Any>(var errorCode : Int ,var message : String) : NetworkResult<T>()
    class Error<T : Any>(var throwable: Throwable) : NetworkResult<T>()
}