package me.gorbuvla.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * Base class for fragments using view binding.
 */
abstract class ViewBindingFragment<VB : ViewBinding> : BaseFragment() {

    protected lateinit var binding: VB

    abstract fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = provideBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindInteraction()
        binding.bindViewModel()
    }

    open fun VB.bindInteraction() {}

    open fun VB.bindViewModel() {}
}