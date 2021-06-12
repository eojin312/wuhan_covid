package assignment.wuhan_covid.senario.main

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import assignment.wuhan_covid.senario.api.ServerConnector
import assignment.wuhan_covid.senario.api.model.Data
import assignment.wuhan_covid.senario.api.model.GetCovidList
import assignment.wuhan_covid.senario.module.dataModel.Pin
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import java.util.ArrayList

class MainViewModel : ViewModel() {
    var isInitLocation = false
    var covidList = MutableLiveData<GetCovidList>()
    val pins = ArrayList<Pin>()
    var prevPin: Pin? = null
    val selectPin = MutableLiveData<Pin>()

    fun getCovidList() {
        ServerConnector.getCovidList("1", "100") {
            covidList.value = it
        }
    }

    fun generatePin(data: Data, naverMap: NaverMap): Pin {
        val marker = Marker().apply {
            position = LatLng(data.lat.toDouble(), data.lng.toDouble())
            map = naverMap
        }

        val pin = Pin(marker, data)
        pin.apply {
            marker.setOnClickListener {
                selectPin(pin)
                false
            }

            resetPinOverlayImage()
        }

        return pin
    }


    private fun selectPin(pin: Pin) {
        prevPin = selectPin.value
        selectPin.value = pin
    }

    fun clearPins() {
        pins.forEach {
            it.marker.map = null
        }
        pins.clear()
    }

    fun generateAddPin(naverMap: NaverMap, data: Data) {
        pins.add(generatePin(data, naverMap))
    }
}