package com.example.bankly_mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.example.bankly_mobile.databinding.FragmentCreditBinding
import org.json.JSONArray


class CreditFragment : Fragment() {

    val creditList = ArrayList<Credits>()
    val creditAdapter = CreditAdapter()

    lateinit var binding: FragmentCreditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreditBinding.inflate(inflater)
        // Inflate the layout for this fragment

        binding.rvCredit.adapter = creditAdapter

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



            creditList.add(Credits( transactionTypeName.toString(),transactionDate.toString(),"₦${aftercharge}",statusName.toString()
            ))
        }

        Toast.makeText(requireContext(),"Paused",Toast.LENGTH_SHORT).show()
        val dialogview = View.inflate(requireContext(),R.layout.dialog_view,null)
        val builder = AlertDialog.Builder(requireContext())
        val filter = requireActivity().findViewById<FrameLayout>(R.id.filter)
        builder.setView(dialogview)
        val dialg = builder.create()

        filter.setOnClickListener {
            dialg.show()
            dialg.window?.setBackgroundDrawableResource(android.R.color.transparent)

            val btn =  dialogview.findViewById<TextView>(R.id.ccl)
            val date =  dialogview.findViewById<TextView>(R.id.itdate)
            val status =  dialogview.findViewById<TextView>(R.id.itstats)
            val nametype =  dialogview.findViewById<TextView>(R.id.ittype)
            date.setOnClickListener {
                onPause()
                dialg.hide()
                creditAdapter.submitList(creditList.sortedBy { it.transactionDate })
            }

            status.setOnClickListener {
                onDestroy()
                dialg.hide()
                creditAdapter.submitList(creditList.sortedBy { it.statusName })
            }

            nametype.setOnClickListener {
                Toast.makeText(requireContext(),"OnResume",Toast.LENGTH_SHORT).show()
                dialg.hide()
                creditAdapter.submitList(creditList.sortedBy { it.transactionTypeName })

            }

            btn.setOnClickListener {
                dialg.hide()

            }
        }


        creditAdapter.submitList(creditList.sortedBy { it.statusName })
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }
    override fun onPause() {
        creditAdapter.submitList(creditList.sortedBy { it.transactionDate })

        super.onPause()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}