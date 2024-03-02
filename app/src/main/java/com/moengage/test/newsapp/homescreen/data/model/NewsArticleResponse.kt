package com.moengage.test.newsapp.homescreen.data.model


import com.google.gson.annotations.SerializedName

data class NewsArticleResponse(
    @SerializedName("articles")
    var articles: List<Article?>? = null,
    @SerializedName("status")
    var status: String? = null
){
    data class Article(
        @SerializedName("author")
        var author: String? = null,
        @SerializedName("content")
        var content: String? = null,
        @SerializedName("description")
        var description: String? = null,
        @SerializedName("publishedAt")
        var publishedAt: String? = null,
        @SerializedName("source")
        var source: Source? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("url")
        var url: String? = null,
        @SerializedName("urlToImage")
        var urlToImage: String? = null
    ){
        data class Source(
            @SerializedName("id")
            var id: String? = null,
            @SerializedName("name")
            var name: String? = null
        )
    }
}