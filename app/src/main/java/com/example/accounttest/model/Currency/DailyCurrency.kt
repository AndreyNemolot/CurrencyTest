package com.example.accounttest.model.Currency

data class DailyCurrency(
    val Date: String="",
    val PreviousDate: String="",
    val PreviousURL: String="",
    val Timestamp: String="",
    val Valute: Valute= Valute()
)