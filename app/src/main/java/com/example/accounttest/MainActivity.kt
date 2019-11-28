package com.example.accounttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.accounttest.fragment.AccountInfoFragment
import com.example.accounttest.fragment.HistoryFragment

import com.example.accounttest.viewModel.MainActivityViewModel

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.accounttest.databinding.ActivityMainBinding
import com.example.accounttest.model.SignHolder
import com.example.accounttest.network.NetworkConnectivityManager


class MainActivity : AppCompatActivity() {

    private lateinit var accountFragment: AccountInfoFragment
    private lateinit var historyFragment: HistoryFragment
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var viewModel: MainActivityViewModel
    private val networkManager = NetworkConnectivityManager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.signHolder = SignHolder

        accountFragment = AccountInfoFragment()
        historyFragment = HistoryFragment()
        setAccountFragment()
        setHistoryFragment()

        viewModel.selectedCurrencyData.observe(this, Observer { changeCurrency(it) })
        connectionCheck()
    }

    private fun connectionCheck() {
        applicationContext
        networkManager.subscribeOnConnectionUnavailable(this,
            Runnable {
                Toast.makeText(this, resources.getString(R.string.no_internet), Toast.LENGTH_SHORT)
                    .show()
            })

        networkManager.subscribeOnConnectionAvailable(this,
            Runnable {
                accountFragment.getData()
                historyFragment.getData()
            })
    }

    private fun changeCurrency(currency: String) {
        accountFragment.changeCurrency(currency)
        historyFragment.changeCurrency(currency)

    }

    private fun setAccountFragment() {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.accountFragmentContainer, accountFragment)
        fragmentTransaction.commit()
    }

    private fun setHistoryFragment() {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.historyFragmentContainer, historyFragment)
        fragmentTransaction.commit()
    }

}
