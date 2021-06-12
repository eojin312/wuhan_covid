package assignment.wuhan_covid.senario.intro

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import assignment.wuhan_covid.senario.api.ServerConnector
import assignment.wuhan_covid.senario.api.model.GetCovidList
import assignment.wuhan_covid.senario.room.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    var covidList = MutableLiveData<GetCovidList>()
    var isProgress = MutableLiveData(false)

    fun getCovidList(context:Context) {
        for(i : Int in 1..10) {
            ServerConnector.getCovidList(i.toString(), "10") {
                CoroutineScope(Dispatchers.IO).launch {
                    AppDatabase.getDatabase(context).apply {
                        dataDao().setData(it.data)
                    }
                }
                covidList.value = it
                Log.d("이어진", "$it")
            }
        }
    }

    fun loadCovidList(context:Context) {
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getDatabase(context).apply {
                var dataList = dataDao().loadAll()
                Log.d("아아아아", "${dataList.size}")
                isProgress.value = true
            }
        }
    }
}