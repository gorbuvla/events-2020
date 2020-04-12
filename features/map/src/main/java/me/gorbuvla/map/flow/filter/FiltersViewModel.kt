package me.gorbuvla.map.flow.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import me.gorbuvla.map.model.FilterRepository
import me.gorbuvla.map.model.filters.FilterOption
import me.gorbuvla.map.model.filters.FilterSection
import me.gorbuvla.ui.livedata.mapNotNull
import me.gorbuvla.ui.util.Text
import me.gorbuvla.ui.viewmodel.State
import me.gorbuvla.ui.viewmodel.launch
import me.gorbuvla.ui.viewmodel.loaded
import me.gorbuvla.ui.viewmodel.value

/**
 * ViewModel for screen with filtering options.
 */
class FiltersViewModel(private val repository: FilterRepository) : ViewModel() {

    private val state = MutableLiveData<State<FilterConfiguration>>()

    val filters: LiveData<FilterConfiguration> = state.mapNotNull { state -> state.value }

    init {
        collect()
    }

    fun toggle(filter: FilterOption) = repository.toggle(filter)

    private fun collect() {
        launch {
            repository.filters.map { filters ->
                FilterConfiguration(
                    sections = listOf(
                        FilterSection(Text.Raw("General"), filters.general),
                        FilterSection(Text.Raw("Event Types"), filters.events)
                    )
                )
            }.collect { filters -> state.loaded(filters) }
        }
    }
}

data class FilterConfiguration(val sections: List<FilterSection>)