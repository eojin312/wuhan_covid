package assignment.wuhan_covid.senario.module

import androidx.fragment.app.FragmentActivity
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

/**
 * Created by GwangMoo You on 4/14/21.
 */
class NaverMapModel {
    fun asyncMap(activity: FragmentActivity, onMapReadyCallback: OnMapReadyCallback, resId: Int) {
        val fm = activity.supportFragmentManager
        val mapFragment = fm.findFragmentById(resId) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(resId, it).commit()
            }
        mapFragment.getMapAsync(onMapReadyCallback)
    }
}