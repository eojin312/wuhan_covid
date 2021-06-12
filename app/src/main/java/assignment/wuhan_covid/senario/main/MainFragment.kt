package assignment.wuhan_covid.senario.main

import android.location.Location
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import assignment.wuhan_covid.R
import assignment.wuhan_covid.databinding.MainFragmentBinding
import assignment.wuhan_covid.senario.module.NaverMapModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource

class MainFragment : Fragment(), OnMapReadyCallback, NaverMap.OnLocationChangeListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding : MainFragmentBinding
    private lateinit var naverMap: NaverMap
    private var locationSource: FusedLocationSource? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(layoutInflater)
        initData()
        initUI()
        setupMap()
        initObserver()
        return binding.root
    }


    private fun initUI() {
        locationSource = FusedLocationSource(this, 0)
        NaverMapModel().asyncMap(requireActivity(), this, R.id.fragment_naver_map)
    }
    private fun initObserver() {
        viewModel.covidList.observe(viewLifecycleOwner) { res ->
            viewModel.clearPins()
            res.data.forEach {data ->
                viewModel.generateAddPin(naverMap, data)
            }
        }

        viewModel.selectPin.observe(viewLifecycleOwner) {
            if(it == null)
                return@observe

            if(viewModel.prevPin == it) {
                it.resetPinOverlayImage()
                viewModel.prevPin = null
                viewModel.selectPin.value = null
            } else {
                viewModel.prevPin?.resetPinOverlayImage()
                it.changeSelectPinOverlayImage()

                naverMap.moveCamera(CameraUpdate.scrollAndZoomTo(it.marker.position, 17.0).animate(CameraAnimation.Easing))
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
            addOnLocationChangeListener(this@MainFragment)
        }
    }


    private fun setupMap() {
        NaverMapModel().asyncMap(requireActivity(), this, R.id.fragment_naver_map)
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