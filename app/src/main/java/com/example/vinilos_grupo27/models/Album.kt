package com.example.vinilos_grupo27.models

import java.io.Serializable


data class Album( val albumId:Int,
                  val name:String,
                  val cover:String,
                  val releaseDate:String,
                  val description:String,
                  val genre:String,
                  val recordLabel:String) : Serializable {
}
