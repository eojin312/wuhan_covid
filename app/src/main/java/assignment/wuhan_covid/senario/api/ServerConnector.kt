package assignment.wuhan_covid.senario.api

import assignment.wuhan_covid.senario.ConstData
import assignment.wuhan_covid.senario.api.model.GetCovidList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServerConnector {
    companion object {
        fun getCovidList(page: String, perPage: String, callback: (getCovidList: GetCovidList) -> Unit) {
            ServerRetrofit.server.getCovidList(page, perPage, ConstData.serviceKey).enqueue {
                if (it != null) {
                    callback(it)
                }
            }
        }

        private inline fun <T> Call<T>.enqueue(crossinline function: (body: T?) -> Unit) {
            //start activity
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    //finish
                    function(response.body())
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    //finish
                }
            })
        }
    }
}