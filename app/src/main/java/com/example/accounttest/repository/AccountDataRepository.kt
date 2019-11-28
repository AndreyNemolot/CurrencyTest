package com.example.accounttest.repository

interface AccountDataRepository<T> {
    fun getAccountProfile(dataReadyCallback: DataReadyCallback<T>)
}