package me.gorbuvla.map.flow.map

import android.view.LayoutInflater
import android.view.ViewGroup
import me.gorbuvla.domain.domain.Coordinate
import me.gorbuvla.map.databinding.FragmentMapBinding
import me.gorbuvla.ui.fragment.ViewBindingFragment

/**
 * Fragment for screen with [Event] & POI map.
 */
class EventMapFragment : ViewBindingFragment<FragmentMapBinding>() {

    interface NavigationDelegate {
        fun open(eventId: String)
        fun navigate(coordinate: Coordinate)
    }

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMapBinding {
        return FragmentMapBinding.inflate(layoutInflater, container, false)
    }
}