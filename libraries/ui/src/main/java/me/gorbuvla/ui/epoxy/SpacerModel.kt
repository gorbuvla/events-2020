package me.gorbuvla.ui.epoxy

import android.view.LayoutInflater
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyAttribute
import me.gorbuvla.ui.R
import me.gorbuvla.ui.databinding.LayoutSpacerBinding
import me.gorbuvla.ui.util.Color
import me.gorbuvla.ui.util.Dimen

/**
 * Epoxy model to insert spacer/decoration between models of same type.
 */
open class SpacerModel: EpoxyModelWithBinding<LayoutSpacerBinding>() {

    @EpoxyAttribute var color: Color = Color.Resource(R.color.colorOnSurface, opacity = 12)
    @EpoxyAttribute var marginStart: Dimen = Dimen.Dp(0)
    @EpoxyAttribute var marginEnd: Dimen = Dimen.Dp(0)

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup): LayoutSpacerBinding {
        return LayoutSpacerBinding.inflate(inflater, container, false)
    }

    override fun LayoutSpacerBinding.bind() {
        spacerFrame.setBackgroundColor(color.resolve(root.context))
        (spacerFrame.layoutParams as? ViewGroup.MarginLayoutParams)?.let {
            it.marginStart = marginStart.resolve(root.context)
            it.marginEnd = marginEnd.resolve(root.context)
        }
    }
}
