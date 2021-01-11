package me.gorbuvla.events.scenes.list.epoxy

import android.view.LayoutInflater
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyAttribute
import me.gorbuvla.events.databinding.LayoutCategoryBinding
import me.gorbuvla.ui.epoxy.EpoxyModelWithBinding
import me.gorbuvla.ui.util.Text

/**
 * Epoxy model for expandable category with arrow.
 */
open class CollapsingCategoryModel : EpoxyModelWithBinding<LayoutCategoryBinding>() {

    @EpoxyAttribute
    lateinit var title: Text

    @EpoxyAttribute
    var expanded: Boolean = false

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onExpand: () -> Unit

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup): LayoutCategoryBinding {
        return LayoutCategoryBinding.inflate(inflater, container, false)
    }

    override fun LayoutCategoryBinding.bind() {
        categoryTitle.text = title.resolve(context)
        categoryTitle.isChecked = expanded
        root.setOnClickListener {
            onExpand()
        }
    }
}