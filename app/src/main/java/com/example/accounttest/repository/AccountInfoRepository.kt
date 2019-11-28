@file:JvmName("AccountDataRepositoryKt")

package com.example.accounttest.repository

import com.example.accounttest.model.UserAccount
import com.example.accounttest.network.Controller
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AccountInfoRepository : AccountDataRepository<UserAccount> {

//    override fun getAccountProfile(dataReadyCallback: DataReadyCallback) {
//        Controller.getUserAccount().enqueue(object : Callback<UserAccount> {
//            override fun onFailure(call: Call<UserAccount>?, t: Throwable?) {
//                failureHandler(dataReadyCallback)
//            }
//            override fun onResponse(call: Call<UserAccount>?, response: Response<UserAccount>?) {
//                if (response != null && response.body() != null && response.isSuccessful) {
//                    dataReadyCallback.dataReady(response.body()!!)
//                } else {
//                    failureHandler(dataReadyCallback)
//                }
//            }
//        })
//    }

    override fun getAccountProfile(dataReadyCallback: DataReadyCallback<UserAccount>) {
        Controller.getUserAccount().enqueue { result ->
            when (result) {
                is Result.Success -> {
                    if (result.response.body() != null && result.response.isSuccessful) {
                        dataReadyCallback.dataReady(result.response.body()!!)
                    } else {
                        dataReadyCallback.dataFailure(UserAccount())
                    }
                }
                is Result.Failure -> {
                    dataReadyCallback.dataFailure(UserAccount())
                }
            }
        }
    }

//    override fun getCurrency() {
//        Controller.getCurrency().enqueue{ result ->
//            when(result) {
//                is Result.Success -> {
//                    //myData will have the type passed from the service method
//                    val myData = result.response.body()
//
//                }
//                is Result.Failure -> {
//                    val myData = result
//                }
//            }
//        }
//    }

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

sealed class Result<T> {
    data class Success<T>(val call: Call<T>, val response: Response<T>) : Result<T>()
    data class Failure<T>(val call: Call<T>, val error: Throwable) : Result<T>()
}