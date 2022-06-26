package id.agunggum.core.data.source.remote.network

import id.agunggum.core.data.source.remote.response.GameResponse
import id.agunggum.core.data.source.remote.response.ListGamesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("games")
    suspend fun getList(): ListGamesResponse

    @GET("games/{id}")
    suspend fun getDetail(@Path("id") gameId: Int): GameResponse
}