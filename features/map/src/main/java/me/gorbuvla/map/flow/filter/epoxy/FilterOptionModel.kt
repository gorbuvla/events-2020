package me.gorbuvla.map.flow.filter.epoxy

import android.view.LayoutInflater
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyAttribute
import me.gorbuvla.map.databinding.LayoutFilterBinding
import me.gorbuvla.map.model.filters.FilterOption
import me.gorbuvla.ui.epoxy.EpoxyModelWithBinding

/**
 * Epoxy Model for single filter option.
 */
open class FilterOptionModel : EpoxyModelWithBinding<LayoutFilterBinding>() {

    @EpoxyAttribute
    lateinit var filter: FilterOption

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onToggle: (FilterOption) -> Unit

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup): LayoutFilterBinding {
        return LayoutFilterBinding.inflate(inflater, container, false)
    }

    override fun LayoutFilterBinding.bind() {
        titleText.text = filter.title.resolve(context)
        checkbox.isChecked = filter.isActive
        root.setOnClickListener {
            onToggle(filter)
        }
    }
}