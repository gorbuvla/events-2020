package me.gorbuvla.navigation.activityflows.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import me.gorbuvla.navigation.R
import me.gorbuvla.navigation.activityflows.home.navigation.HomeFlowCoordinatorImpl
import me.gorbuvla.navigation.databinding.ActivityHomeBinding
import me.gorbuvla.navigation.utils.ViewBindingActivity
import me.gorbuvla.navigation.utils.setupWithNavController
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

/**
 * Activity for home flow with bottom navigation.
 */
class HomeFlowActivity : ViewBindingActivity<ActivityHomeBinding>() {

    companion object {
        const val SCOPE_NAME = "scope+home"
    }

    private lateinit var navigator: HomeFlowCoordinatorImpl

    override val flowScope: Scope
        get() = getKoin().getOrCreateScope(SCOPE_NAME, named(SCOPE_NAME))

    override fun provideBinding(inflater: LayoutInflater) = ActivityHomeBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = flowScope.get()
        navigator.activity = this
        lifecycle.addObserver(navigator)

        if (savedInstanceState == null) {
            setupBottomNavigation()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navGraphs = listOf(R.navigation.map_navigation, R.navigation.list_navigation)

        val controllerChanges = binding.bottomNavigation.setupWithNavController(navGraphs, supportFragmentManager, R.id.fragment_container, intent)
        controllerChanges.observe(this, Observer {
            navigator.navigationController = it
        })
    }
}