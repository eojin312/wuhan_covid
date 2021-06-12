package assignment.wuhan_covid.senario.api.model

data class GetCovidList(
    val currentCount: Int,
    val data: List<Data>,
    val page: Int,
    val perPage: Int,
    val totalCount: Int
)