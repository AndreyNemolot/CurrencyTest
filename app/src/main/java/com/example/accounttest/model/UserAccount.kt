package com.example.accounttest.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableDouble

data class UserAccount(
    var card_number: String = "",
    var cardholder_name: String = "",
    var valid: String = "",
    var balance: Double = 0.0,
    var transaction_history: ArrayList<TransactionHistoryItem> = ArrayList()

)
