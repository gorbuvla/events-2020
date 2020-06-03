package me.gorbuvla.events.screens.detail

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import me.gorbuvla.domain.domain.Coordinate
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.R
import me.gorbuvla.events.databinding.FragmentEventDetailBinding
import me.gorbuvla.events.screens.detail.epoxy.EventDetailEpoxyController
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
        fun openLink(url: String)
        fun navigate(coordinate: Coordinate)
        fun open(eventId: String)
    }

    private val navigationDelegate: NavigationDelegate by flow()
    private val viewModel: EventDetailViewModel by viewModel { params(requireArguments()) }
    private val controller: EventDetailEpoxyController by lazy {
        EventDetailEpoxyController(
            onStartNavigation = { navigationDelegate.navigate(it) },
            onSimilarClick = { navigationDelegate.open(it.id) }
        )
    }

    override val hasBackNavigation: Boolean = true
    override val hasToolbarMenu: Boolean = true

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEventDetailBinding {
        return FragmentEventDetailBinding.inflate(inflater, container, false)
    }

    override fun FragmentEventDetailBinding.bindInteraction() {
        recyclerView.adapter = controller.adapter
        toolbar.setNavigationOnClickListener {
            navigationDelegate.navigateUp()
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_event, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val event = viewModel.event.value?.event
        if (item.itemId == R.id.open_link && event != null) {
            navigationDelegate.openLink(event.url)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}