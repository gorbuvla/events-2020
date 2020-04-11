package me.gorbuvla.events.flow.list

import android.view.LayoutInflater
import android.view.ViewGroup
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.databinding.FragmentEventListBinding
import me.gorbuvla.ui.fragment.ViewBindingFragment
import me.gorbuvla.ui.fragment.flow
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for screen with list of events.
 */
class EventListFragment : ViewBindingFragment<FragmentEventListBinding>() {

    interface NavigationDelegate {

        fun open(event: Event)
    }

    private val delegate: NavigationDelegate by flow()
    private val viewModel: EventListViewModel by viewModel()


    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEventListBinding {
        return FragmentEventListBinding.inflate(inflater, container, false)
    }
}