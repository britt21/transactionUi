package com.example.bankly_mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bankly_mobile.databinding.FragmentDebitBinding
import org.json.JSONArray


class DebitFragment : Fragment() {

    val debitList = ArrayList<Debits>()
    val debitAdapter = DebitAdapter()

    lateinit var binding: FragmentDebitBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDebitBinding.inflate(inflater)
        // Inflate the layout for this fragment

        binding.rvDebit.adapter = debitAdapter



        val jsonData = requireActivity().applicationContext.resources.openRawResource(
            requireContext().applicationContext.resources.getIdentifier(
                "pos","raw",requireContext().applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }


        val outputJsonString = JSONArray(jsonData)

        for (i in 0 until  outputJsonString.length()){

            val transactionTypeName = outputJsonString.getJSONObject(i).get("transactionTypeName")
            val transactionDate = outputJsonString.getJSONObject(i).get("transactionDate")
            val aftercharge = outputJsonString.getJSONObject(i).get("balanceAfterTransaction")
            val beforecharge = outputJsonString.getJSONObject(i).get("balanceBeforeTransaction")
            val statusName = outputJsonString.getJSONObject(i).get("statusName")

            val debitbalance   = aftercharge.toString().toDouble().minus(beforecharge.toString().toDouble())


            debitList.add(Debits( transactionTypeName.toString(),transactionDate.toString(),"- ₦${debitbalance.toString().replace("-","")}",statusName.toString()
            ))
        }



        debitAdapter.submitList(debitList)

        return  binding.root
    }

}