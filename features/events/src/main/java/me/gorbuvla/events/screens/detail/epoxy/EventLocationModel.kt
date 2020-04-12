package me.gorbuvla.events.screens.detail.epoxy

import android.view.LayoutInflater
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyAttribute
import me.gorbuvla.domain.domain.Address
import me.gorbuvla.domain.domain.Coordinate
import me.gorbuvla.events.databinding.LayoutEventLocationBinding
import me.gorbuvla.ui.epoxy.EpoxyModelWithBinding

/**
 * Epoxy Model for [Address] items.
 */
open class EventLocationModel : EpoxyModelWithBinding<LayoutEventLocationBinding>() {

    @EpoxyAttribute
    lateinit var address: Address

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onAddressClick: (Coordinate) -> Unit

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup): LayoutEventLocationBinding {
        return LayoutEventLocationBinding.inflate(inflater, container, false)
    }

    override fun LayoutEventLocationBinding.bind() {
        addressText.text = address.name
        root.setOnClickListener {
            onAddressClick(address.location)
        }
    }
}