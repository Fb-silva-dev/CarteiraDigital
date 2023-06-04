package dev.mlds.walletrequest.service

import dev.mlds.walletrequest.model.Card
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServiceCard {

    @GET("cards")
    fun getAll() : Call<List<Card>>

    @GET("cards/{id}")
    fun getByID(@Path("id") cardId: String) : Call<Card>

    @POST("cards/")
    fun createCard(@Body card: Card) : Call<Card>

    @PUT("cards/{id}")
    fun updateCard(@Path("id") cardId: String, @Body card: Card) : Call<Card>

    @DELETE("cards/{id}")
    fun deleteCard(@Path("id") cardId: String) : Call<Card>

    @DELETE("cards")
    fun deleteAll() : Call<Any>

}