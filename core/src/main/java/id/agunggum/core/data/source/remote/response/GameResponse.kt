package id.agunggum.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(

	@field:SerializedName("added")
	val added: Int? = null,

	@field:SerializedName("name_original")
	val nameOriginal: String? = null,

	@field:SerializedName("metacritic")
	val metacritic: Int? = null,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("description_raw")
	val descriptionRaw: String,

	@field:SerializedName("game_series_count")
	val gameSeriesCount: Int? = null,

	@field:SerializedName("playtime")
	val playtime: Int? = null,

	@field:SerializedName("metacritic_url")
	val metacriticUrl: String? = null,

	@field:SerializedName("alternative_names")
	val alternativeNames: List<String?>? = null,

	@field:SerializedName("parents_count")
	val parentsCount: Int? = null,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem?>? = null,

	@field:SerializedName("metacritic_platforms")
	val metacriticPlatforms: List<MetacriticPlatformsItem?>? = null,

	@field:SerializedName("creators_count")
	val creatorsCount: Int? = null,

	@field:SerializedName("rating_top")
	val ratingTop: Int? = null,

	@field:SerializedName("reviews_text_count")
	val reviewsTextCount: String? = null,

	@field:SerializedName("achievements_count")
	val achievementsCount: Int? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("reddit_url")
	val redditUrl: String? = null,

	@field:SerializedName("reddit_name")
	val redditName: String? = null,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int? = null,

	@field:SerializedName("reddit_count")
	val redditCount: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("released")
	val released: String? = null,

	@field:SerializedName("parent_achievements_count")
	val parentAchievementsCount: String? = null,

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("suggestions_count")
	val suggestionsCount: Int? = null,

	@field:SerializedName("youtube_count")
	val youtubeCount: String? = null,

	@field:SerializedName("additions_count")
	val additionsCount: Int? = null,

	@field:SerializedName("movies_count")
	val moviesCount: Int? = null,

	@field:SerializedName("twitch_count")
	val twitchCount: String? = null,

	@field:SerializedName("background_image_additional")
	val backgroundImageAdditional: String? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("tba")
	val tba: Boolean? = null,

	@field:SerializedName("esrb_rating")
	val esrbRating: EsrbRating? = null,

	@field:SerializedName("screenshots_count")
	val screenshotsCount: Int? = null,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("reddit_description")
	val redditDescription: String? = null,

	@field:SerializedName("reactions")
	val reactions: Reactions? = null,

	@field:SerializedName("reddit_logo")
	val redditLogo: String? = null,

	@field:SerializedName("updated")
	val updated: String? = null
)

data class MetacriticPlatformsItem(

	@field:SerializedName("metascore")
	val metascore: Int? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Requirements(

	@field:SerializedName("minimum")
	val minimum: String? = null,

	@field:SerializedName("recommended")
	val recommended: String? = null
)

data class Reactions(
	val any: Any? = null
)

data class Platform(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class EsrbRating(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class PlatformsItem(

	@field:SerializedName("requirements")
	val requirements: Requirements? = null,

	@field:SerializedName("released_at")
	val releasedAt: String? = null,

	@field:SerializedName("platform")
	val platform: Platform? = null
)

