package com.example.accounttest.model.Currency

data class RUB(
    override val CharCode: String="RUB",
    override val ID: String="",
    override val Name: String="",
    override val Nominal: Int=1,
    override val NumCode: String="",
    override val Previous: Double=0.0,
    override val Value: Double=1.0
): Currency()