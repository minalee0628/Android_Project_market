package com.example.appproject.data

data class ItemModel(
    val sellerId : String,
    val title : String,
    val date : Long,
    val content : String,
    val price : String,
    val sellOrNot :String,
) {
    constructor() : this("","",0,"","","")
}