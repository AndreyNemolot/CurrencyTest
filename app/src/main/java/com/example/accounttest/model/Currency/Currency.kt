package com.example.accounttest.model.Currency

abstract class Currency(){
    abstract val CharCode: String
    abstract val ID: String
    abstract val Name: String
    abstract val Nominal: Int
    abstract val NumCode: String
    abstract val Previous: Double
    abstract val Value: Double
}