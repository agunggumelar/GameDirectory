package id.agunggum.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGamesResponse(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(

	@field:SerializedName("added")
	val added: Int? = null,

	@field:SerializedName("suggestions_count")
	val suggestionsCount: Int? = null,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("metacritic")
	val metacritic: Int? = null,

	@field:SerializedName("playtime")
	val playtime: Int? = null,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem?>? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("tba")
	val tba: Boolean? = null,

	@field:SerializedName("esrb_rating")
	val esrbRating: EsrbRating? = null,

	@field:SerializedName("rating_top")
	val ratingTop: Int? = null,

	@field:SerializedName("reviews_text_count")
	val reviewsTextCount: String? = null,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int? = null,

	@field:SerializedName("updated")
	val updated: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("released")
	val released: String
)


