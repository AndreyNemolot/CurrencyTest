package com.example.accounttest.repository

interface CurrencyDataRepository<T> {
    fun getCurrencyData(dataReadyCallback: DataReadyCallback<T>)
}