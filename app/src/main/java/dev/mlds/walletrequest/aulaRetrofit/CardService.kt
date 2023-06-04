package dev.mlds.walletrequest.aulaRetrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CardService {
    @GET("cards")
    fun findAll(): Call<List<Card>>

    @GET("cards/{id}")
    fun findById(@Path("id") cardId: String): Call<Card>

    @POST("cards")
    fun createCard(@Body card: Card): Call<Card>
}