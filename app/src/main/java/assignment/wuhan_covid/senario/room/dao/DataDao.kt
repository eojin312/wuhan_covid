package assignment.wuhan_covid.senario.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import assignment.wuhan_covid.senario.api.model.Data

@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setData(data: List<Data>)

    @Query("SELECT * FROM data")
    fun loadAll(): List<Data>
}