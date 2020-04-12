package me.gorbuvla.navigation.utils

import android.content.Intent
import android.util.SparseArray
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.core.util.forEach
import androidx.core.util.set
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

/**
 * Extensions to have [BottomNavigationView] supporting multiple backstacks.
 * @see https://github.com/android/architecture-components-samples/tree/master/NavigationAdvancedSample
 */
internal fun BottomNavigationView.setupWithNavController(navGraphs: List<Int>, fragmentManager: FragmentManager, containerId: Int, intent: Intent): LiveData<NavController> {

    val tags = SparseArray<String>()
    val selectedNavController = MutableLiveData<NavController>()

    var firstFragmentGraphId = 0

    navGraphs.forEachIndexed { index, navGraphId ->
        val tag = fragmentTagFor(index)

        val navHostFragment = fragmentManager.findNavHostFragment(tag, navGraphId, containerId)
        val graphId = navHostFragment.navController.graph.id

        if (index == 0) {
            firstFragmentGraphId = graphId
        }

        tags[graphId] = tag

        if (selectedItemId == graphId) {
            selectedNavController.value = navHostFragment.navController
            fragmentManager.attachNavHostFragment(navHostFragment, index == 0)
        } else {
            fragmentManager.detachNavHostFragment(navHostFragment)
        }
    }

    var selectedItemTag = tags[selectedItemId]
    val firstFragmentTag = tags[firstFragmentGraphId]
    var isOnFirstFragment = selectedItemTag == firstFragmentTag

    setOnNavigationItemSelectedListener { item ->
        if (fragmentManager.isStateSaved) {
            false
        } else {
            val newlySelectedItemTag = tags[item.itemId]
            if (selectedItemTag != newlySelectedItemTag) {
                fragmentManager.popBackStack(firstFragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)

                val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag) as NavHostFragment

                if (firstFragmentTag != newlySelectedItemTag) {
                    fragmentManager.beginTransaction()
                        .attach(selectedFragment)
                        .setPrimaryNavigationFragment(selectedFragment)
                        .apply {
                            tags.forEach { _, frag ->
                                if (frag != newlySelectedItemTag) {
                                    detach(fragmentManager.findFragmentByTag(firstFragmentTag)!!)
                                }
                            }
                        }
                        .addToBackStack(firstFragmentTag)
                        .setReorderingAllowed(true)
                        .commit()
                }

                selectedItemTag = newlySelectedItemTag
                isOnFirstFragment = selectedItemTag == firstFragmentTag
                selectedNavController.value = selectedFragment.navController
                true
            } else {
                false
            }
        }
    }

    setupItemReselection(tags, fragmentManager)

    setupDeepLinks(navGraphs, fragmentManager, containerId, intent)

    fragmentManager.addOnBackStackChangedListener {
        if (!isOnFirstFragment && !fragmentManager.isOnBackStack(firstFragmentTag)) {
            selectedItemId = firstFragmentGraphId
        }

        selectedNavController.value?.let { controller ->
            if (controller.currentDestination == null) {
                controller.navigate(controller.graph.id)
            }
        }
    }

    return selectedNavController
}

private fun BottomNavigationView.setupDeepLinks(navGraphIds: List<Int>, fragmentManager: FragmentManager, containerId: Int, intent: Intent) {
    navGraphIds.indices.zip(navGraphIds).firstOrNull { (index, graphId) ->
        val tag = fragmentTagFor(index)

        val navHostFragment = fragmentManager.findNavHostFragment(tag, graphId, containerId)

        val isItemToSelect = with(navHostFragment.navController) {
            handleDeepLink(intent) && selectedItemId != graph.id
        }

        if (isItemToSelect) {
            selectedItemId = navHostFragment.navController.graph.id
        }

        isItemToSelect
    } ?: Timber.e("Unable to handle deeplink: $intent")
}

private fun BottomNavigationView.setupItemReselection(navGraphTags: SparseArray<String>, fragmentManager: FragmentManager) {
    setOnNavigationItemReselectedListener { item ->
        val tag = navGraphTags[item.itemId]
        val fragment = fragmentManager.findFragmentByTag(tag) as NavHostFragment
        val navController = fragment.navController

        navController.popBackStack(navController.graph.startDestination, false)
    }
}

private fun FragmentManager.attachNavHostFragment(navHostFragment: NavHostFragment, isPrimaryNavFragment: Boolean) {
    beginTransaction()
        .attach(navHostFragment)
        .also { if (isPrimaryNavFragment) it.setPrimaryNavigationFragment(navHostFragment) }
        .commitNow()
}

private fun FragmentManager.detachNavHostFragment(navHostFragment: NavHostFragment) {
    beginTransaction()
        .detach(navHostFragment)
        .commitNow()
}

private fun FragmentManager.findNavHostFragment(tag: String, @NavigationRes navGraphId: Int, @IdRes containerId: Int): NavHostFragment {
    return when (val fragment = findFragmentByTag(tag) as? NavHostFragment) {
        is NavHostFragment -> fragment
        else -> {
            NavHostFragment.create(navGraphId).also {
                beginTransaction()
                    .add(containerId, it, tag)
                    .commitNow()
            }
        }
    }
}

private fun FragmentManager.isOnBackStack(backStackName: String): Boolean {
    return (0 until backStackEntryCount).firstOrNull { index -> getBackStackEntryAt(index).name == backStackName } != null
}

private fun fragmentTagFor(index: Int) = "bottomNavigation#$index"