package me.gorbuvla.map.screens.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import me.gorbuvla.map.databinding.FragmentFilterBinding
import me.gorbuvla.map.screens.filter.epoxy.FiltersEpoxyController
import me.gorbuvla.ui.fragment.ViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment with list of filtering options.
 */
class FiltersFragment : ViewBindingFragment<FragmentFilterBinding>() {

    private val viewModel: FiltersViewModel by viewModel()

    private val controller by lazy {
        FiltersEpoxyController(onToggle = { viewModel.toggle(it) })
    }

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFilterBinding {
        return FragmentFilterBinding.inflate(inflater, container, false)
    }

    override fun FragmentFilterBinding.bindInteraction() {
        recyclerView.adapter = controller.adapter
    }

    override fun FragmentFilterBinding.bindViewModel() {
        viewModel.filters.observe(viewLifecycleOwner, Observer { filters ->
            controller.setData(filters)
        })
    }
}