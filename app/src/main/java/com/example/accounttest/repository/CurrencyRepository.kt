package com.example.accounttest.repository

import com.example.accounttest.model.Currency.DailyCurrency
import com.example.accounttest.network.Controller
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyRepository : CurrencyDataRepository<DailyCurrency> {


    override fun getCurrencyData(dataReadyCallback: DataReadyCallback<DailyCurrency>) {
        Controller.getCurrency().enqueue { result ->
            when (result) {
                is Result.Success -> {
                    if (result.response.body() != null && result.response.isSuccessful) {
                        dataReadyCallback.dataReady(result.response.body()!!)
                    } else {
                        dataReadyCallback.dataFailure(DailyCurrency())
                    }
                }
                is Result.Failure -> {
                    dataReadyCallback.dataFailure(DailyCurrency())
                }
            }
        }
    }


}

private inline fun <reified T> Call<T>.enqueue(crossinline result: (Result<T>) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, error: Throwable) {
            result(Result.Failure(call, error))
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            result(Result.Success(call, response))
        }
    })
}