package com.example.accounttest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel : ViewModel() {

    var selectedCurrencyData = MutableLiveData<String>()

    fun changeCurrency(currency: String) {
        selectedCurrencyData.postValue(currency)
    }
}