package com.moengage.test.newsapp.homescreen.data.dto


import androidx.compose.runtime.Stable
import com.google.gson.annotations.SerializedName
import kotlin.random.Random


/**
 * Data class representing the response containing news articles.
 * @property articles List of articles retrieved from the API.
 * @property status Status of the response.
 */
@Stable
data class NewsArticleResponse(
    @SerializedName("articles")
    var articles: List<Article?>? = null,
    @SerializedName("status")
    var status: String? = null
) {
    /**
     * Data class representing a single news article.
     * @property author The author of the article.
     * @property content The content of the article.
     * @property description A short description of the article.
     * @property publishedAt The date and time the article was published.
     * @property source Information about the source of the article.
     * @property title The title of the article.
     * @property url The URL of the article.
     * @property urlToImage The URL of the image associated with the article.
     * @property id Unique identifier for the article.
     */
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
        var urlToImage: String? = null,
        val id: Int = Random.nextInt()
    ) {
        /**
         * Data class representing the source of a news article.
         * @property id The unique identifier of the source.
         * @property name The name of the source.
         */
        data class Source(
            @SerializedName("id")
            var id: String? = null,
            @SerializedName("name")
            var name: String? = null
        )
    }
}