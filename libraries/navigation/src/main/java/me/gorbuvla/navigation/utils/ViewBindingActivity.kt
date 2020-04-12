package me.gorbuvla.navigation.utils

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import me.gorbuvla.ui.activity.FlowActivity

/**
 * Base Activity to be used with ViewBinding.
 */
abstract class ViewBindingActivity<VB : ViewBinding> : FlowActivity() {

    protected lateinit var binding: VB

    abstract fun provideBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = provideBinding(layoutInflater)
        setContentView(binding.root)
    }
}
