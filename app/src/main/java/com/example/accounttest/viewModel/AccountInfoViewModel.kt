package com.example.accounttest.viewModel

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.accounttest.Exchanger
import com.example.accounttest.model.Currency.DailyCurrency
import com.example.accounttest.model.SignHolder
import com.example.accounttest.model.UserAccount
import com.example.accounttest.repository.AccountInfoRepository
import com.example.accounttest.repository.CurrencyRepository
import com.example.accounttest.repository.DataReadyCallback

class AccountInfoViewModel : ViewModel() {

    private var userAccount = UserAccount()
    private var dailyCurrency = DailyCurrency()
    private var exchanger: Exchanger? = null
    var selectedCurrency = "GBP"

    var currentSign = ObservableField<String>(SignHolder.getSignFromCurrency(selectedCurrency))
    var employee = ObservableField<UserAccount>()

    var exchangedBalance = 0.0
    var selectedCurrencyBalance: ObservableDouble = ObservableDouble(exchangedBalance)
    var liveCurrencyData = MutableLiveData<DailyCurrency>()


    fun getAccountData() {
        AccountInfoRepository().getAccountProfile(object :
            DataReadyCallback<UserAccount> {
            override fun dataFailure(data: UserAccount) {
                saveUserAccountInfo(UserAccount())
            }

            override fun dataReady(data: UserAccount) {
                saveUserAccountInfo(data)
            }
        })
    }

    private fun saveUserAccountInfo(data: UserAccount) {
        userAccount = data
        employee.set(userAccount)
    }

    fun getCurrencyData(): MutableLiveData<DailyCurrency> {
        CurrencyRepository().getCurrencyData(object :
            DataReadyCallback<DailyCurrency> {
            override fun dataReady(data: DailyCurrency) {
                saveCurrency(data)
            }

            override fun dataFailure(data: DailyCurrency) {
                saveCurrency(data)
            }
        })
        return liveCurrencyData
    }

    private fun saveCurrency(data: DailyCurrency) {
        dailyCurrency = data
        exchanger = Exchanger(dailyCurrency.Valute)
        exchangeCurrency(selectedCurrency)
    }

    fun exchangeCurrency(newCurrency: String) {
        if (exchanger != null) {
            selectedCurrency = newCurrency
            currentSign.set(SignHolder.getSignFromCurrency(selectedCurrency))
            exchangedBalance = exchanger!!.change(userAccount.balance, "USD", selectedCurrency)
            selectedCurrencyBalance.set(exchangedBalance)
        }
    }


}
