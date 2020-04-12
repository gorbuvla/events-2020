package me.gorbuvla.map.flow.map.util

import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import me.gorbuvla.map.R

class BitmapDescriptorProvider {

    val event: BitmapDescriptor by lazy {
        BitmapDescriptorFactory.fromResource(R.drawable.ic_event_marker)
    }

    val place: BitmapDescriptor by lazy {
        BitmapDescriptorFactory.fromResource(R.drawable.ic_place_marker)
    }
}