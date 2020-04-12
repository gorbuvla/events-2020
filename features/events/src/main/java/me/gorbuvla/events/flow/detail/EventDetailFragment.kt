package me.gorbuvla.events.flow.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import me.gorbuvla.domain.domain.Coordinate
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.databinding.FragmentEventDetailBinding
import me.gorbuvla.events.flow.detail.epoxy.EventDetailEpoxyController
import me.gorbuvla.ui.fragment.BaseActionDelegate
import me.gorbuvla.ui.fragment.ViewBindingFragment
import me.gorbuvla.ui.fragment.flow
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.DefinitionParameters
import org.koin.core.parameter.parametersOf

/**
 * Fragment for screen with [Event] detail.
 */
class EventDetailFragment : ViewBindingFragment<FragmentEventDetailBinding>() {

    companion object {

        private const val ID_KEY = "id+key"

        fun arguments(eventId: String): Bundle {
            return bundleOf(ID_KEY to eventId)
        }

        private fun params(arguments: Bundle): DefinitionParameters {
            return parametersOf(arguments.getString(ID_KEY))
        }
    }

    interface NavigationDelegate : BaseActionDelegate {
        fun navigate(coordinate: Coordinate)
        fun open(eventId: String)
    }

    private val delegate: NavigationDelegate by flow()
    private val viewModel: EventDetailViewModel by viewModel { params(requireArguments()) }
    private val controller: EventDetailEpoxyController by lazy {
        EventDetailEpoxyController(
            onStartNavigation = { delegate.navigate(it) },
            onSimilarClick = { delegate.open(it.id) }
        )
    }

    override val hasBackNavigation: Boolean = true

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEventDetailBinding {
        return FragmentEventDetailBinding.inflate(inflater, container, false)
    }

    override fun FragmentEventDetailBinding.bindInteraction() {
        recyclerView.adapter = controller.adapter
        toolbar.setNavigationOnClickListener {
            delegate.navigateUp()
        }
    }

    override fun FragmentEventDetailBinding.bindViewModel() {
        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading) progress.show() else progress.hide()
        })

        viewModel.event.observe(viewLifecycleOwner, Observer { (event, similar) ->
            toolbar.title = event.title
            controller.setData(event, similar)
        })
    }
}