package com.example.bankly_mobile

import android.content.Context
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bankly_mobile.databinding.FragmentAllBinding
import org.json.JSONArray
import org.json.JSONObject

class AllFragment : Fragment() {
    val allList = ArrayList<AllList>()
    val allAdapter = AllAdapter()


    lateinit var binding : FragmentAllBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAllBinding.inflate(inflater)

        binding.rvAllList.adapter = allAdapter

        val search = requireActivity().findViewById<SearchView>(R.id.searchit)


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
            val credit = outputJsonString.getJSONObject(i).get("credit")



            allList.add(
                AllList( transactionTypeName.toString(),transactionDate.toString(),"₦${aftercharge}","₦${beforecharge}".toString(),statusName.toString(),credit.toString()
            )
            )
        }


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
                onDestroy()
                onPause()
                dialg.hide()
            }

            status.setOnClickListener {
                onDestroy()
                dialg.hide()
                allAdapter.submitList(allList.sortedBy { it.statusName })
            }


            nametype.setOnClickListener {
                onDestroy()
                nametype.requestLayout()
                nametype.refreshDrawableState()
                nametype.invalidate()
                nametype.invalidate()
                dialg.hide()
                allAdapter.submitList(allList.sortedBy { it.transactionTypeName })
            }

            btn.setOnClickListener {
                btn.invalidate()
                onDestroy()
                btn.requestLayout()
                dialg.hide()
            }
        }

        allAdapter.submitList(allList)
        return binding.root
    }

    override fun onPause() {
        super.onPause()
    }
}