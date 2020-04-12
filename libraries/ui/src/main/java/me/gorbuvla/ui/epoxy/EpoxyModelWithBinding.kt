package me.gorbuvla.ui.epoxy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.EpoxyModelWithView
import me.gorbuvla.ui.R

/**
 * Base Epoxy model to be subclassed in order to build components with viewbinding.
 */
abstract class EpoxyModelWithBinding<VB: ViewBinding> : EpoxyModelWithView<View>() {

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup): VB

    abstract fun VB.bind()

    open fun VB.unbind() {}

    override fun buildView(parent: ViewGroup): View {
        val binding = createBinding(LayoutInflater.from(parent.context), parent)
        val view = binding.root
        view.setTag(R.id.epoxy_model_tag, binding)
        return view
    }

    override fun bind(view: View) {
        super.bind(view)

        @Suppress("UNCHECKED_CAST")
        (view.getTag(R.id.epoxy_model_tag) as VB).bind()
    }

    override fun unbind(view: View) {
        super.unbind(view)

        @Suppress("UNCHECKED_CAST")
        (view.getTag(R.id.epoxy_model_tag) as VB).unbind()
    }

    val VB.context: Context
        get() = root.context
}
