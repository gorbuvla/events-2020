package me.gorbuvla.map.screens.map.util

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

/**
 * Custom cluster rendered to manage clustered items.
 */
class ClusterRenderer(
    context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<MarkerItem>,
    private val bitmapProvider: BitmapDescriptorProvider
) : DefaultClusterRenderer<MarkerItem>(context, map, clusterManager) {

    override fun shouldRenderAsCluster(cluster: Cluster<MarkerItem>): Boolean {
        return false
    }

    override fun onBeforeClusterItemRendered(item: MarkerItem, markerOptions: MarkerOptions) {
        markerOptions.icon(item.getBitmapDescriptor(bitmapProvider))
    }

    override fun onClusterItemRendered(item: MarkerItem, marker: Marker) {
        super.onClusterItemRendered(item, marker)
        marker.tag = item // 🤷‍
    }
}