package me.gorbuvla.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import me.gorbuvla.ui.R
import me.gorbuvla.ui.activity.FlowActivity
import me.gorbuvla.ui.error.UIError

/**
 * Base [Fragment] to reduce boilerplate code.
 */
abstract class BaseFragment : Fragment() {

    open val hasToolbarMenu: Boolean = false
    open val hasBackNavigation: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById(R.id.toolbar) as? Toolbar
        toolbar?.title = ""
        setHasOptionsMenu(hasToolbarMenu)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(hasBackNavigation)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowHomeEnabled(hasBackNavigation)
    }

    // TODO: customize default errors in future
    /**
     * Default [UIError] presenter.
     * Snackbars behaviour is specific based on the view under which they are shown.
     * By calling this extension on specific CoordinatorLayout we achieve desired snackbar behaviour.
     */
    protected open fun View.handleUIError(uiError: UIError) {
        when (uiError) {
            is UIError.Dialog -> {
                AlertDialog.Builder(requireContext())
                    .setTitle(uiError.titleRes)
                    .setMessage(uiError.bodyRes)
                    .setPositiveButton(android.R.string.ok, { _, _ -> })
                    .show()
            }
            is UIError.Snackbar -> {
                Snackbar.make(this, uiError.textRes, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}

inline fun <reified T> BaseFragment.flow(): Lazy<T> = lazy { (requireActivity() as FlowActivity).flowScope.get<T>() }