package me.gorbuvla.events.screens.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import me.gorbuvla.events.databinding.FragmentEventListBinding
import me.gorbuvla.events.screens.list.epoxy.EventListEpoxyController
import me.gorbuvla.ui.fragment.ViewBindingFragment
import me.gorbuvla.ui.fragment.flow
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for screen with list of events.
 */
class EventListFragment : ViewBindingFragment<FragmentEventListBinding>() {

    interface NavigationDelegate {
        fun open(eventId: String)
    }

    private val navigationDelegate: NavigationDelegate by flow()
    private val viewModel: EventListViewModel by viewModel()
    private val controller: EventListEpoxyController by lazy {
        EventListEpoxyController(
            onToggleCategory = { viewModel.toggle(it) },
            onEventClick = { navigationDelegate.open(it.id) }
        )
    }

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEventListBinding {
        return FragmentEventListBinding.inflate(inflater, container, false)
    }

    override fun FragmentEventListBinding.bindInteraction() {
        recyclerView.adapter = controller.adapter
        toolbar.title = "Event List"

        swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun FragmentEventListBinding.bindViewModel() {
        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) progress.show() else progress.hide()
        })

        viewModel.reloading.observe(viewLifecycleOwner, Observer { isReloading ->
            swipeRefresh.isRefreshing = isReloading
        })

        viewModel.events.observe(viewLifecycleOwner, Observer { categories ->
            controller.setData(categories)
        })
    }
}