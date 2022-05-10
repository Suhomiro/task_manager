package midis.example.task_manager.model.room.history_task

import androidx.room.*
import midis.example.task_manager.model.data.DayData
import midis.example.task_manager.model.data.TaskData

@Dao
interface HistoryTaskDao {

    @TypeConverters(Convertors::class)
    @Query("SELECT * FROM day_data")
    suspend fun getTask(): List<DayData>

    @Query("SELECT * FROM day_data ")
    suspend fun data(): DayData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dayData: DayData)

    @Update
    suspend fun update(dayData: DayData)

    @Delete
    suspend fun delete(dayData: DayData)
}