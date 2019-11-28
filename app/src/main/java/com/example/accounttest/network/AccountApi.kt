package com.example.accounttest.network

import com.example.accounttest.model.Currency.DailyCurrency
import com.example.accounttest.model.UserAccount
import retrofit2.Call
import retrofit2.http.GET

interface AccountApi {
    @GET("tasktest/data.php")
    fun getUserAccount(
    ): Call<UserAccount>

    @GET("daily_json.js")
    fun getCurrency(
    ): Call<DailyCurrency>
}