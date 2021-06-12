package assignment.wuhan_covid.senario.module.dataModel

import assignment.wuhan_covid.R
import assignment.wuhan_covid.senario.ConstData
import assignment.wuhan_covid.senario.api.model.Data
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

/**
 * Created by GwangMoo You on 5/24/21.
 */
data class Pin(
    val marker: Marker,
    val data: Data
) {
    fun resetPinOverlayImage() {
        marker.icon = OverlayImage.fromResource(
            when (data.centerType) {
                ConstData.centerTypeCenter -> R.drawable.ic_pin_activated
                ConstData.centerTypeArea -> R.drawable.ic_pin_waiting
                else -> R.drawable.ic_pin_activated
            }
        )
        marker.zIndex = 100
    }

    fun changeSelectPinOverlayImage() {
        marker.icon = OverlayImage.fromResource(
            when (data.centerType) {
                ConstData.centerTypeCenter -> R.drawable.ic_pin_select_activated
                ConstData.centerTypeArea -> R.drawable.ic_pin_select_waiting
                else -> R.drawable.ic_pin_select_activated
            }
        )
        marker.zIndex = 1000
    }
}