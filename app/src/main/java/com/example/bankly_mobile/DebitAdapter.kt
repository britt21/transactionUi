package com.example.bankly_mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DebitAdapter : ListAdapter<Debits, DebitAdapter.DebitViewHolder>(Differ) {
    object Differ : DiffUtil.ItemCallback<Debits>() {
        override fun areItemsTheSame(oldItem: Debits, newItem: Debits): Boolean {

            return oldItem.charges === newItem.charges
        }

        override fun areContentsTheSame(oldItem: Debits, newItem: Debits): Boolean {
            return oldItem.charges == newItem.charges

        }


    }

    class DebitViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

        val transactionTypeName = view.findViewById<TextView>(R.id.transname)
        val transactionDate = view.findViewById<TextView>(R.id.transDate)
        val charges = view.findViewById<TextView>(R.id.transAmount)
        val statusName = view.findViewById<TextView>(R.id.transStats)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebitViewHolder {
        return DebitViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.debit_list,parent,false))
    }

    override fun onBindViewHolder(holder: DebitViewHolder, position: Int) {
        val curList = getItem(position)
        holder.transactionTypeName.text = curList.transactionTypeName
        holder.transactionDate.text = curList.transactionDate
        holder.charges.text = curList.charges
        holder.statusName.text = curList.statusName

    }
}

data class Debits(
    val transactionTypeName: String? = null,
    val transactionDate: String? = null,
    val charges: String? = null,
    val statusName: String? = null
)