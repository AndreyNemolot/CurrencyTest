package com.example.accounttest.adapter


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableField
import com.example.accounttest.Exchanger
import com.example.accounttest.databinding.HistoryListItemBinding
import com.example.accounttest.model.TransactionHistoryItem
import com.example.accounttest.R
import com.example.accounttest.model.SignHolder
import java.math.BigDecimal
import java.math.RoundingMode


class RecyclerAdapter :
    RecyclerView.Adapter<RecyclerAdapter.NoteHolder>() {
    private var historyList = ArrayList<TransactionHistoryItem>()
    var multiplier = 1.0
    var currencySign = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_list_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = historyList[position]
        holder.binding(currentNote)
    }

    fun setRepositories(notes: ArrayList<TransactionHistoryItem>, multiple: Double, currencySign: String) {
        this.currencySign= currencySign
        this.multiplier=multiple
        this.historyList = notes
        notifyDataSetChanged()

    }
    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = HistoryListItemBinding.bind(itemView)

        fun binding(historyItem: TransactionHistoryItem) {
            val doubleAmount = historyItem.amount.toDouble()
            val decimal = BigDecimal(multiplier*doubleAmount).setScale(2, RoundingMode.HALF_EVEN)
            historyItem.changedAmount=decimal.toDouble()
            historyItem.currencySign=currencySign
            binding.historyItem = historyItem
        }
    }

}