package assignment.wuhan_covid.senario.api

import assignment.wuhan_covid.senario.ConstData
import assignment.wuhan_covid.senario.api.model.GetCovidList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServer {
    @GET(ConstData.api_covid_list)
    fun getCovidList(@Query("page") page: String, @Query("perPage") perPage: String, @Query("serviceKey") serviceKey: String) : Call<GetCovidList>

}