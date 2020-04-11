package me.gorbuvla.events.flow.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.databinding.FragmentEventDetailBinding
import me.gorbuvla.ui.fragment.ViewBindingFragment

/**
 * Fragment for screen with [Event] detail.
 */
class EventDetailFragment : ViewBindingFragment<FragmentEventDetailBinding>() {

    interface NavigationDelegate {

        fun open(event: Event)
    }

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEventDetailBinding {
        return FragmentEventDetailBinding.inflate(inflater, container, false)
    }
}