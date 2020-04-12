package me.gorbuvla.map.screens.map.util

import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import me.gorbuvla.map.model.POI

sealed class MarkerItem : ClusterItem {

    abstract fun getBitmapDescriptor(provider: BitmapDescriptorProvider): BitmapDescriptor

    data class Event(val event: POI.Event) : MarkerItem() {
        override fun getTitle() = event.event.title
        override fun getSnippet() = event.event.description
        override fun getPosition(): LatLng = event.event.address.location.let { LatLng(it.latitude, it.longitude) }
        override fun getBitmapDescriptor(provider: BitmapDescriptorProvider) = provider.event
    }

    data class Place(val place: POI.Stop) : MarkerItem() {
        override fun getTitle() = place.place.name
        override fun getSnippet() = null
        override fun getPosition(): LatLng = place.place.address.location.let { LatLng(it.latitude, it.longitude) }
        override fun getBitmapDescriptor(provider: BitmapDescriptorProvider) = provider.place
    }
}