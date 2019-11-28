package com.example.accounttest.model

object SignHolder {

    val GBP_SIGN ="£"
    val EUR_SIGN ="€"
    val RUB_SIGN ='\u20BD'.toString()

    val GBP ="GBP"
    val EUR ="EUR"
    val RUB ="RUB"


    fun getSignFromCurrency(currency: String): String{
        when(currency){
            "GBP" -> return GBP_SIGN
            "EUR" -> return EUR_SIGN
            "RUB" -> return RUB_SIGN
        }
        return ""
    }
}