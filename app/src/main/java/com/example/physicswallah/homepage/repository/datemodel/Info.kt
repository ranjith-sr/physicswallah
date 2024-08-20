package com.example.physicswallah.homepage.repository.datemodel

data class Info(
    var count : Int ,
    var pages : Int ,
    var next : String? = null ,
    var prev : String? = null
)