package com.example.physicswallah.homepage.repository.datemodel

data class Character(
    var id : Int ,
    var name : String,
    var status : String,
    var species : String,
    var type : String,
    var gender : String,
    var origin : LocationInfo,
    var location : LocationInfo,
    var image : String,
    var episode : List<String>,
    var url : String,
    var created : String
)