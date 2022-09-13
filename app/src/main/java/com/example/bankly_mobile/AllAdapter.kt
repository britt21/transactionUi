package com.example.bankly_mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class AllAdapter : ListAdapter<AllList, AllAdapter.AllViewHolder>(Differ) {
    object Differ : DiffUtil.ItemCallback<AllList>() {
        override fun areItemsTheSame(oldItem: AllList, newItem: AllList): Boolean {

            return oldItem.charges === newItem.charges
        }

        override fun areContentsTheSame(oldItem: AllList, newItem: AllList): Boolean {
            return oldItem.charges == newItem.charges

        }


    }

    class AllViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

        val transactionTypeName = view.findViewById<TextView>(R.id.transname)
        val transactionDate = view.findViewById<TextView>(R.id.transDate)
        val charges = view.findViewById<TextView>(R.id.transAmount)
        val statusName = view.findViewById<TextView>(R.id.transStats)
        val arrow = view.findViewById<ImageView>(R.id.arrow)
        val bckbtn = view.findViewById<FrameLayout>(R.id.bckbtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllViewHolder {
        return AllViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.all_list,parent,false))
    }

    override fun onBindViewHolder(holder: AllViewHolder, position: Int) {
        val curList = getItem(position)
        holder.transactionTypeName.text = curList.transactionTypeName
        holder.transactionDate.text = curList.transactionDate
        holder.charges.text = curList.charges
        holder.charges.text = curList.bcharges
        holder.statusName.text = curList.statusName
        if (curList.credit!!.contains("null")){
            holder.arrow.setImageResource(R.drawable.ic_arra_trans)
            holder.arrow.setBackgroundResource(R.drawable.trans_bg)

        }else{
            holder.arrow.setImageResource(R.drawable.ic_credit_arr)
            holder.bckbtn.setBackgroundResource(R.drawable.green_trans_bg)


        }

    }
}

data class AllList(
    val transactionTypeName: String? = null,
    val transactionDate: String? = null,
    val charges: String? = null,
    val bcharges: String? = null,
    val statusName: String? = null,
    val credit: String? = null
)