package me.gorbuvla.map.screens.map

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.map
import me.gorbuvla.map.model.FilterRepository
import me.gorbuvla.map.model.POI
import me.gorbuvla.map.model.POIRepository
import me.gorbuvla.ui.livedata.mapNotNull
import me.gorbuvla.ui.util.Color
import me.gorbuvla.ui.R
import me.gorbuvla.ui.viewmodel.State
import me.gorbuvla.ui.viewmodel.loaded
import me.gorbuvla.ui.viewmodel.value

/**
 * ViewModel for events & places map screen.
 */
class EventMapViewModel(
    private val poiRepository: POIRepository,
    private val filterRepository: FilterRepository
) : ViewModel() {

    private val state = MutableLiveData<State<List<POI>>>()
    private val filtersChanged = MutableLiveData<Boolean>()

    val poi: LiveData<List<POI>> = state.mapNotNull { state -> state.value }

    val fabColor: LiveData<Color> = filtersChanged.map { changed -> Color.Resource(if (changed) R.color.colorOrange else R.color.colorGreen) }

    init {
        collect()
    }

    private fun collect() {
        filterRepository.filters.combine(poiRepository.poi) { filters, data -> filters.apply(data) }
            .onEach { state.loaded(it) }
            .launchIn(viewModelScope)

        filterRepository.defaultFiltersChanged
            .map { changed -> filtersChanged.value = changed }
            .launchIn(viewModelScope)
    }
}