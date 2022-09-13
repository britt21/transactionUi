package com.example.bankly_mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CreditAdapter : ListAdapter<Credits, CreditAdapter.CreditViewHolder>(Differ) {
    object Differ : DiffUtil.ItemCallback<Credits>() {
        override fun areItemsTheSame(oldItem: Credits, newItem: Credits): Boolean {

            return oldItem.charges === newItem.charges
        }

        override fun areContentsTheSame(oldItem: Credits, newItem: Credits): Boolean {
            return oldItem.charges == newItem.charges

        }


    }

    class CreditViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

        val transactionTypeName = view.findViewById<TextView>(R.id.ctransname)
        val transactionDate = view.findViewById<TextView>(R.id.cdate)
        val charges = view.findViewById<TextView>(R.id.ctransAmount)
        val statusName = view.findViewById<TextView>(R.id.ctransStats)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        return CreditViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.credit_list,parent,false))
    }

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        val curList = getItem(position)
        holder.transactionTypeName.text = curList.transactionTypeName
        holder.transactionDate.text = curList.transactionDate
        holder.charges.text = curList.charges
        holder.statusName.text = curList.statusName

    }
}

data class Credits(
    val transactionTypeName: String? = null,
    val transactionDate: String? = null,
    val charges: String? = null,
    val statusName: String? = null
)