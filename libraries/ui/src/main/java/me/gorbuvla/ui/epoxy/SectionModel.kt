package me.gorbuvla.ui.epoxy

import android.view.LayoutInflater
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyAttribute
import me.gorbuvla.ui.databinding.LayoutSectionBinding
import me.gorbuvla.ui.util.Text

/**
 * Epoxy Model for list section title.
 */
open class SectionModel : EpoxyModelWithBinding<LayoutSectionBinding>() {

    @EpoxyAttribute
    lateinit var title: Text

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup) = LayoutSectionBinding.inflate(inflater, container, false)

    override fun LayoutSectionBinding.bind() {
        sectionText.text = title.resolve(context)
    }

}