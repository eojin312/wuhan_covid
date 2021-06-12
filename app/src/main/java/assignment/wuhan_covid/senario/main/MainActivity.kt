package assignment.wuhan_covid.senario.main

import android.location.Location
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import assignment.wuhan_covid.R
import assignment.wuhan_covid.databinding.ActivityMainBinding
import assignment.wuhan_covid.databinding.MainFragmentBinding
import assignment.wuhan_covid.senario.module.NaverMapModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource


class MainActivity : AppCompatActivity(), OnMapReadyCallback, NaverMap.OnLocationChangeListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        initData()
        initObserver()
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    private lateinit var naverMap: NaverMap
    private var locationSource: FusedLocationSource? = null


    private fun initUI() {
        locationSource = FusedLocationSource(this, 0)
        NaverMapModel().asyncMap(this, this, R.id.fragment_naver_map)
    }
    private fun initObserver() {
        viewModel.covidList.observe(this) { res ->
            viewModel.clearPins()
            res.data.forEach {data ->
                viewModel.generateAddPin(naverMap, data)
            }
        }

        viewModel.selectPin.observe(this) {
            if(it == null)
                return@observe

            if(viewModel.prevPin == it) {
                it.resetPinOverlayImage()
                viewModel.prevPin = null
                viewModel.selectPin.value = null
            } else {
                viewModel.prevPin?.resetPinOverlayImage()
                it.changeSelectPinOverlayImage()

                naverMap.moveCamera(
                    CameraUpdate.scrollAndZoomTo(it.marker.position, 17.0).animate(
                        CameraAnimation.Easing))
            }
        }
    }

    private fun initData() {
        viewModel.getCovidList()
    }

    override fun onMapReady(p0: NaverMap) {
        naverMap = p0.apply {
            uiSettings.isZoomControlEnabled = false
            uiSettings.isCompassEnabled = false
            locationSource = this.locationSource
            locationTrackingMode = LocationTrackingMode.NoFollow
            addOnLocationChangeListener(this@MainActivity)
        }
    }


    private fun setupMap() {
        NaverMapModel().asyncMap(this, this, R.id.fragment_naver_map)
    }

    override fun onLocationChange(p0: Location) {
        if (!viewModel.isInitLocation) {
            viewModel.isInitLocation = true
            moveToCurrentLocation()
        }
    }

    private fun moveToCurrentLocation() {
        if (locationSource?.lastLocation == null) {
            return
        }

        val latLng = LatLng(
            locationSource!!.lastLocation!!.latitude,
            locationSource!!.lastLocation!!.longitude
        )
        naverMap?.moveCamera(CameraUpdate.scrollAndZoomTo(latLng, 15.0).animate(CameraAnimation.Easing))
    }
}