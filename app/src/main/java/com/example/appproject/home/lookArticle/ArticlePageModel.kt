package com.example.appproject.home.lookArticle

data class ArticlePageModel (
    val buyerId : String,
    val sellerId : String,
    val itemTitle : String,
    val itemPrice : String,
    val itemContent : String,
    val sellOrNot : String,
    val key: Long,
) {
    constructor(): this("","","", "", "", "",0)
}