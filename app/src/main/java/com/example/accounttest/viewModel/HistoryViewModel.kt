package com.example.accounttest.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.accounttest.Exchanger
import com.example.accounttest.model.Currency.DailyCurrency
import com.example.accounttest.model.SignHolder

import com.example.accounttest.model.UserAccount
import com.example.accounttest.repository.AccountInfoRepository
import com.example.accounttest.repository.CurrencyRepository
import com.example.accounttest.repository.DataReadyCallback

class HistoryViewModel : ViewModel() {

    var userAccount = UserAccount()
    private var selectedCurrency: String = "GBP"
    private var exchanger: Exchanger? = null
    private var dailyCurrency = DailyCurrency()
    private var userAccountLiveData = MutableLiveData<UserAccount>()

    var currentSign = SignHolder.getSignFromCurrency(selectedCurrency)
    var currentCurrency = "GBP"

    fun getAccountData(): MutableLiveData<UserAccount> {
        AccountInfoRepository().getAccountProfile(object :
            DataReadyCallback<UserAccount> {
            override fun dataFailure(data: UserAccount) {
                saveUserAccountInfo(UserAccount())
            }

            override fun dataReady(data: UserAccount) {
                saveUserAccountInfo(data)
            }
        })
        return userAccountLiveData
    }

    fun getCurrencyData() {
        CurrencyRepository().getCurrencyData(object :
            DataReadyCallback<DailyCurrency> {
            override fun dataReady(data: DailyCurrency) {
                saveCurrency(data)
            }

            override fun dataFailure(data: DailyCurrency) {
                saveCurrency(data)
            }
        })
    }

    private fun saveCurrency(data: DailyCurrency) {
        dailyCurrency = data
        exchanger = Exchanger(dailyCurrency.Valute)
    }

    fun getMultiplier(newCurrency: String): Double {
        if (exchanger != null) {
            selectedCurrency = newCurrency
            currentSign = (SignHolder.getSignFromCurrency(selectedCurrency))
            return exchanger!!.change(1.0, "USD", selectedCurrency)
        }
        return 1.0
    }

    private fun saveUserAccountInfo(data: UserAccount) {
        userAccount = data
        userAccountLiveData.postValue(userAccount)
    }
}
