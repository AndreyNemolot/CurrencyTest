package com.example.accounttest

import com.example.accounttest.model.Currency.Currency
import com.example.accounttest.model.Currency.RUB
import com.example.accounttest.model.Currency.Valute
import java.math.BigDecimal
import java.math.RoundingMode

class Exchanger(val valute: Valute) {

    private var currencyMap: HashMap<String, Currency> = HashMap()

    init {
        currencyMapping()
    }

    private fun currencyMapping() {
        currencyMap[valute.EUR.CharCode] = valute.EUR
        currencyMap[valute.USD.CharCode] = valute.USD
        currencyMap[valute.GBP.CharCode] = valute.GBP
        currencyMap[valute.RUB.CharCode] = valute.RUB
    }

    fun change(sum: Double, changedCurrency: String, newCurrency: String):Double{
        if (currencyMap[changedCurrency]!=null && currencyMap[newCurrency]!=null){
            val changedCurrency = currencyMap[changedCurrency]
            val newCurrency = currencyMap[newCurrency]
            val value= (sum*(changedCurrency!!.Value/changedCurrency.Nominal))/(newCurrency!!.Value/newCurrency.Nominal)
            val decimal = BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN)
            return decimal.toDouble()
        }

        return 1.0
    }
}