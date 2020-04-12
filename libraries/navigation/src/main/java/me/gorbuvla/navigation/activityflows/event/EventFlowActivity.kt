package me.gorbuvla.navigation.activityflows.event

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import me.gorbuvla.navigation.R
import me.gorbuvla.navigation.activityflows.event.navigation.EventFlowCoordinatorImpl
import me.gorbuvla.navigation.databinding.ActivityEventBinding
import me.gorbuvla.navigation.utils.ViewBindingActivity
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

/**
 * Activity for Event Detail screen flow.
 */
internal class EventFlowActivity : ViewBindingActivity<ActivityEventBinding>() {

    companion object {
        private const val EVENT_ID_KEY = "event+id"
        const val SCOPE_NAME = "scope+event"

        fun arguments(eventId: String): Bundle {
            return bundleOf(EVENT_ID_KEY to eventId)
        }
    }

    private val eventId: String by lazy {
        intent.extras?.getString(EVENT_ID_KEY) ?: throw IllegalStateException("Event ID not provided for Event Detail Flow")
    }

    private lateinit var navigator: EventFlowCoordinatorImpl

    override fun provideBinding(inflater: LayoutInflater) = ActivityEventBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigator = flowScope.get()
        navigator.activity = this
        navigator.navigationController = findNavController(R.id.fragment_container)
        lifecycle.addObserver(navigator)

        if (savedInstanceState == null) {
            navigator.open(eventId)
        }
    }

    override val flowScope: Scope
        get() = getKoin().getOrCreateScope(SCOPE_NAME, named(SCOPE_NAME))
}