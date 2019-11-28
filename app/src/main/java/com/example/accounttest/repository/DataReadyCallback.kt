package com.example.accounttest.repository

interface DataReadyCallback<T> {
    fun dataReady(data: T)
    fun dataFailure(data: T)

}