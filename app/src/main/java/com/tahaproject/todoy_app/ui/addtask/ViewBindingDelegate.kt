package com.tahaproject.todoy_app.ui.addtask

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewBindingDelegate<T : ViewBinding>(private val bindingInflater: (LayoutInflater) -> T) {

    private var _binding: T? = null
    val binding: T
        get() = _binding ?: throw IllegalStateException("ViewBinding is accessed before initialization or after destruction.")

    fun clearBinding() {
        _binding = null
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (_binding == null) {
            _binding = bindingInflater(LayoutInflater.from((thisRef as Fragment).requireContext()))
        }
        return binding
    }
}