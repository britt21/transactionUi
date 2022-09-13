package com.example.bankly_mobile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


val Titles = arrayOf(
    "All","Credit","Debit"
)
class ViewPagerAdapter(val fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val allfragment = listOf(
        AllFragment(),CreditFragment(), DebitFragment()
    )

    override fun getCount(): Int {
        return allfragment.size
    }

    override fun getItem(position: Int): Fragment{
        return  allfragment.get(position)

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return Titles[position]
    }
}