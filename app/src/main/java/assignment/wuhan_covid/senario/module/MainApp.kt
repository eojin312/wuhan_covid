package assignment.wuhan_covid.senario.module

import android.app.Application
import android.content.Context
import assignment.wuhan_covid.senario.main.MainActivity

class MainApp: Application() {
    companion object {
        lateinit var ctx: Context

        var mainActivity: MainActivity? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = this
    }
}