package com.tahaproject.todoy_app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentHomeBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition
import kotlin.math.log

class FragmentHome : BaseFragmentWithTransition<FragmentHomeBinding>() {

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

}
