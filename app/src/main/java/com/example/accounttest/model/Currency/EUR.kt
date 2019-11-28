package com.example.accounttest.model.Currency

data class EUR(
    override val CharCode: String="",
    override val ID: String="",
    override val Name: String="",
    override val Nominal: Int=0,
    override val NumCode: String="",
    override val Previous: Double=0.0,
    override val Value: Double=0.0
): Currency()