package dev.mlds.walletrequest.aulaRetrofit

val cardList: List<Card> = emptyList()

data class Card(
    val id: String,
    val name: String,
    val cvv: String,
    val number: String,
    val expirationDate: String,
    val cardType: String
)
