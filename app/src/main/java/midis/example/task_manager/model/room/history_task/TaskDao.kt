package midis.example.task_manager.model.room.history_task

import androidx.room.*
import midis.example.task_manager.model.data.TaskData

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_data")
    suspend fun getTask(): List<TaskData>

    @Query("SELECT * FROM task_data ")
    suspend fun data(): TaskData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskData: TaskData)

    @Update
    suspend fun update(taskData: TaskData)

    @Delete
    suspend fun delete(taskData: TaskData)
}