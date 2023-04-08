package com.tahaproject.todoy_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentHomeBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition

class FragmentHome : BaseFragmentWithTransition<FragmentHomeBinding>() {

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addCallBacks()
    }

    private fun addCallBacks(){
        binding.viewAllTeam.setOnClickListener {


        }


    }

}