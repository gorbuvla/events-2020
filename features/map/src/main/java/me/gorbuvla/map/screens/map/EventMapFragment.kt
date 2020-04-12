package me.gorbuvla.map.screens.map

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.gorbuvla.domain.domain.Coordinate
import me.gorbuvla.map.R
import me.gorbuvla.map.databinding.FragmentMapBinding
import me.gorbuvla.map.screens.map.util.BitmapDescriptorProvider
import me.gorbuvla.map.screens.map.util.ClusterRenderer
import me.gorbuvla.map.screens.map.util.MarkerItem
import me.gorbuvla.map.model.POI
import me.gorbuvla.ui.fragment.ViewBindingFragment
import me.gorbuvla.ui.fragment.flow
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.flowbinding.android.view.clicks

/**
 * Fragment for screen with [Event] & POI map.
 */
class EventMapFragment : ViewBindingFragment<FragmentMapBinding>() {

    interface NavigationDelegate {
        fun open(eventId: String)
        fun navigate(coordinate: Coordinate)
    }

    private val delegate: NavigationDelegate by flow()
    private val viewModel: EventMapViewModel by viewModel()

    private var googleMap: GoogleMap? = null
    private val bitmapProvider = BitmapDescriptorProvider()
    private var clusterManager: ClusterManager<MarkerItem>? = null


    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMapBinding {
        return FragmentMapBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync { map ->
            googleMap = map
            clusterManager = clusterManagerFor(map)

            /**
             * When navigating to another tab, view gets destroyed.
             * Once we return back, clusterManager points to old map ref, so new should be created.
             * Problem is, that we obtain a new reference to map, but drawn markers are cached and when
             * we try to clear all markers with new cluster manager, old (cached) ones are not removed because
             * new cluster manager does not know about those, so map should be cleared and latest POI should be
             * drawn again so that new clusterManager manages latest markers.
             */
            map.clear()
            redraw(viewModel.poi.value ?: emptyList())
            observeData()
            map.applyStyle()
            map.setOnInfoWindowClickListener { marker ->
                when (val item = marker.tag) {
                    is MarkerItem.Event -> delegate.open(item.event.event.id)
                    is MarkerItem.Place -> delegate.navigate(item.place.place.address.location)
                }
            }
        }
    }

    override fun FragmentMapBinding.bindInteraction() {
        fabFilter.clicks()
            .onEach { drawerLayout.openDrawer(GravityCompat.END) }
            .launchIn(lifecycleScope)
    }

    private fun observeData() {
        viewModel.poi.observe(viewLifecycleOwner, Observer { poi ->
            redraw(poi)
        })

        viewModel.fabColor.observe(viewLifecycleOwner, Observer { color ->
            binding.fabFilter.drawable.setTint(Color.WHITE)
            binding.fabFilter.backgroundTintList = ColorStateList.valueOf(color.resolve(requireContext()))
        })
    }

    private fun redraw(poi: List<POI>) {
        clusterManager?.run {
            clearItems()
            addItems(poi.map { it.toMarkerItem() })
            cluster()
        }
    }

    private fun POI.toMarkerItem(): MarkerItem {
        return when (this) {
            is POI.Event -> MarkerItem.Event(this)
            is POI.Stop -> MarkerItem.Place(this)
        }
    }

    private fun GoogleMap.applyStyle() {
        isIndoorEnabled = false
        uiSettings.isCompassEnabled = false
        uiSettings.isMapToolbarEnabled = false
        uiSettings.isMyLocationButtonEnabled = false
        uiSettings.isRotateGesturesEnabled = false
    }

    private fun clusterManagerFor(map: GoogleMap): ClusterManager<MarkerItem> {
        return ClusterManager<MarkerItem>(requireContext(), map).also {
            it.renderer = ClusterRenderer(requireContext(), map, it, bitmapProvider)
            map.setOnCameraIdleListener(it)
            map.setOnMarkerClickListener(it)
        }
    }
}