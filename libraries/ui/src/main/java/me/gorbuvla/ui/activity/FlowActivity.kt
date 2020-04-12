package me.gorbuvla.ui.activity

import androidx.appcompat.app.AppCompatActivity
import org.koin.core.scope.Scope

/**
 * Base Activity for each app flow.
 */
abstract class FlowActivity : AppCompatActivity() {

    abstract val flowScope: Scope

//    override fun onDestroy() {
//        super.onDestroy()
//        if (!isChangingConfigurations && !flowScope.closed) {
//            flowScope.close()
//        }
//    }
}