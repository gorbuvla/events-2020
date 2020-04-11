package me.gorbuvla.navigation.activityflows.event

import android.view.LayoutInflater
import me.gorbuvla.navigation.databinding.ActivityEventBinding
import me.gorbuvla.navigation.utils.ViewBindingActivity
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

/**
 * TODO add class description
 */
internal class EventFlowActivity : ViewBindingActivity<ActivityEventBinding>() {

    companion object {
        const val SCOPE_NAME = "scope+event"
    }

    override val flowScope: Scope
        get() = getKoin().getOrCreateScope(SCOPE_NAME, named(SCOPE_NAME))

    override fun provideBinding(inflater: LayoutInflater) = ActivityEventBinding.inflate(inflater)
}