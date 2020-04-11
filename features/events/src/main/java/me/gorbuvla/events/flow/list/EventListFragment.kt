package me.gorbuvla.events.flow.list

import android.view.LayoutInflater
import android.view.ViewGroup
import me.gorbuvla.core.domain.Event
import me.gorbuvla.events.databinding.FragmentEventListBinding
import me.gorbuvla.ui.fragment.ViewBindingFragment

/**
 * Fragment for screen with list of events.
 */
class EventListFragment : ViewBindingFragment<FragmentEventListBinding>() {

    interface NavigationDelegate {

        fun open(event: Event)
    }

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEventListBinding {
        return FragmentEventListBinding.inflate(inflater, container, false)
    }
}