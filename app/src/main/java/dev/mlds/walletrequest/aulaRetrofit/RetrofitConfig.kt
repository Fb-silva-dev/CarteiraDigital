package dev.mlds.walletrequest.aulaRetrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

    private val retroConf = Retrofit.Builder()
        .baseUrl("https://wallet-backend-theta.vercel.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cardService: CardService = retroConf.create(CardService::class.java)

}