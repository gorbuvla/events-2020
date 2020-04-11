package me.gorbuvla.ui.viewmodel

import androidx.lifecycle.MutableLiveData

/**
 * Base state that can be maintained by any view model
 **/
sealed class State<out T> {
    object Loading : State<Nothing>()
    object Empty : State<Nothing>()
    object Idle : State<Nothing>()
    data class Error(val error: Throwable) : State<Nothing>()
    data class Loaded<out T>(val data: T) : State<T>()
    data class Reloading<out T>(val previousData: T?) : State<T>()
}

val State<*>.error: Throwable?
    get() = (this as? State.Error)?.error

val <T> State<T>.value: T?
    get() = (this as? State.Loaded)?.data

/**
 * Extensions on [MutableLiveData] to reduce boilerplate code
 */

fun <T> MutableLiveData<State<T>>.loading() {
    value = State.Loading
}

fun <T> MutableLiveData<State<T>>.reloadingWithPrevious() {
    val previous = (value as? State.Loaded)?.data
    value = State.Reloading(previous)
}

fun <T> MutableLiveData<State<T>>.reloading(previous: T? = null) {
    value = State.Reloading(previous)
}

fun <T> MutableLiveData<State<T>>.loaded(data: T) {
    value = State.Loaded(data)
}

fun MutableLiveData<State<Unit>>.loaded() {
    value = State.Loaded(Unit)
}

fun <T> MutableLiveData<State<T>>.error(err: Throwable) {
    value = State.Error(err)
}