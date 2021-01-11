package me.gorbuvla.events.scenes.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.domain.domain.EventType
import me.gorbuvla.domain.model.EventRepository
import me.gorbuvla.ui.livedata.mapNotNull
import me.gorbuvla.ui.util.Result
import me.gorbuvla.ui.viewmodel.*

/**
 * TODO add class description
 */
class EventListViewModel(private val repository: EventRepository) : ViewModel() {

    private val state = MutableLiveData<State<List<EventCategory>>>()

    val loading: LiveData<Boolean> = state.map { state -> state is State.Loading }
    val reloading: LiveData<Boolean> = state.map { state -> state is State.Reloading }
    val events: LiveData<List<EventCategory>> = state.mapNotNull { state -> state.value }

    init {
        state.loading()
        loadLatest()
    }

    fun refresh() {
        state.reloadingWithPrevious()
        loadLatest()
    }

    fun toggle(category: EventType) = state.toggleCategory(category)

    private fun loadLatest() {
        launch {
            when (val result = wrapResult { repository.events() }) {
                is Result.success -> state.loaded(result.value.categories)
                is Result.failure -> state.error(result.error)
            }
        }
    }

    private fun MutableLiveData<State<List<EventCategory>>>.toggleCategory(type: EventType) {
        val currentValue = value
        if (currentValue != null && currentValue is State.Loaded) {
            value = State.Loaded(
                currentValue.data.map { if (it.type == type) it.copy(isExpanded = !it.isExpanded) else it }
            )
        }
    }
}

private val List<Event>.categories: List<EventCategory>
    get() = groupBy { it.type }.map { (type, events) ->
        EventCategory(type, events, isExpanded = true)
    }

data class EventCategory(
    val type: EventType,
    val events: List<Event>,
    val isExpanded: Boolean
)