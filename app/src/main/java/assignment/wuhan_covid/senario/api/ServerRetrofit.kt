package assignment.wuhan_covid.senario.api

import assignment.wuhan_covid.senario.ConstData

class ServerRetrofit {
    companion object {
        private val retrofit = RetrofitBuilder.buildServerRetrofit(ConstData.SERVER_BASE_URL)
        val server: APIServer = retrofit.create(APIServer::class.java)
    }
}