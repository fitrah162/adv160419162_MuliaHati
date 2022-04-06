package com.ubaya.a160419162_muliahati.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.a160419162_muliahati.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imgButtonDonasi.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDonasiListFragment()
            Navigation.findNavController(it).navigate(action)
        }

        imgButtonZakat.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToZakatListFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}