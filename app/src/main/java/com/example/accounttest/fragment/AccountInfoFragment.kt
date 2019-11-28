package com.example.accounttest.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.accounttest.databinding.AcountInfoFragmentBinding
import com.example.accounttest.viewModel.AccountInfoViewModel


class AccountInfoFragment : Fragment() {

    private lateinit var viewModel: AccountInfoViewModel
    lateinit var binding: AcountInfoFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AcountInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AccountInfoViewModel::class.java)
        getData()
        binding.viewModel = viewModel

    }

    fun getData() {
        viewModel.getAccountData()
        viewModel.getCurrencyData()
    }

    fun changeCurrency(currency: String) {
        viewModel.selectedCurrency = currency
        viewModel.exchangeCurrency(currency)
    }
}
