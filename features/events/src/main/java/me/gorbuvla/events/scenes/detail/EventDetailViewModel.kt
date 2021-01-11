package me.gorbuvla.events.scenes.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.domain.model.EventRepository
import me.gorbuvla.ui.livedata.mapNotNull
import me.gorbuvla.ui.util.Result
import me.gorbuvla.ui.viewmodel.*

/**
 * Fragment for event detail screen.
 */
class EventDetailViewModel(
    private val eventId: String,
    private val repository: EventRepository
) : ViewModel() {

    private val state = MutableLiveData<State<EventDetail>>()

    val loading: LiveData<Boolean> = state.mapNotNull { state -> state is State.Loading }
    val event: LiveData<EventDetail> = state.mapNotNull { state -> state.value }

    init {
        loadEvent()
    }

    private fun loadEvent() {
        state.loading()
        launch {
            when (val result  = wrapResult { EventDetail(event = repository.event(eventId), similar = repository.similar(eventId)) }) {
                is Result.success -> state.loaded(result.value)
                is Result.failure -> state.error(result.error)
            }
        }
    }
}

data class EventDetail(
    val event: Event,
    val similar: List<Event>
)