package com.example.accounttest.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.accounttest.adapter.RecyclerAdapter

import com.example.accounttest.databinding.HistoryFragmentBinding
import com.example.accounttest.model.SignHolder
import com.example.accounttest.model.UserAccount
import com.example.accounttest.viewModel.HistoryViewModel

class HistoryFragment : Fragment() {

    private lateinit var viewModel: HistoryViewModel
    private lateinit var binding: HistoryFragmentBinding
    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        getData()
        adapter = RecyclerAdapter()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

    }

    fun getData() {
        viewModel.getCurrencyData()
        viewModel.getAccountData().observe(this, Observer { setData(it) })
    }

    private fun setData(userAccount: UserAccount) {
        viewModel.userAccount = userAccount
        changeCurrency(viewModel.currentCurrency)
    }

    fun changeCurrency(currency: String) {
        viewModel.currentCurrency = currency
        val multiplier = viewModel.getMultiplier(viewModel.currentCurrency)
        adapter.setRepositories(
            viewModel.userAccount.transaction_history,
            multiplier,
            SignHolder.getSignFromCurrency(viewModel.currentCurrency)
        )
    }

}
