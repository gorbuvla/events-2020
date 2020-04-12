package me.gorbuvla.ui.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * Mutating [LiveData] extensions.
 */

fun <T, Y> LiveData<T>.mapNotNull(mapper: (T) -> Y?): LiveData<Y> {
    val mediator = MediatorLiveData<Y>()
    mediator.addSource(this) { item ->
        val mapped = mapper(item)
        if (mapped != null) {
            mediator.value = mapped
        }
    }
    return mediator
}

fun <T> LiveData<T>.asEvent(): LiveEvent<T> {
    val mediator = MediatorLiveData<Event<T>>()
    mediator.addSource(this) { item ->
        mediator.value = Event(item)
    }
    return mediator
}
