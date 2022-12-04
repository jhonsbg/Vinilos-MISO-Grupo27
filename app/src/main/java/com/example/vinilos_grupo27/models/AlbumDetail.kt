package com.example.vinilos_grupo27.models

data class AlbumDetail(
    var albumId:Int,
    var name:String,
    var cover:String,
    var releaseDate:String,
    var description:String,
    var genre:String,
    var recordLabel:String
)
{
    constructor(): this(0,"","","","","","" ) {  }
}