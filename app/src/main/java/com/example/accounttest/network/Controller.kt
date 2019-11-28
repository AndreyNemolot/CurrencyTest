package com.example.accounttest.network

import com.example.accounttest.model.Currency.DailyCurrency
import com.example.accounttest.model.UserAccount
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Controller {
    private val BASE_URL_ACCOUNT = "https://peterpartner.net"
    private val BASE_URL_HISTORY = "https://www.cbr-xml-daily.ru"

    private var accountRetrofit: Retrofit
    private var historyRetrofit: Retrofit


    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        accountRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_ACCOUNT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        historyRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_HISTORY)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getUserAccount(): Call<UserAccount> {
        val accountApi = accountRetrofit.create(AccountApi::class.java)
        return accountApi.getUserAccount()
    }

    fun getCurrency(): Call<DailyCurrency> {
        val accountApi = historyRetrofit.create(AccountApi::class.java)
        return accountApi.getCurrency()
    }


}