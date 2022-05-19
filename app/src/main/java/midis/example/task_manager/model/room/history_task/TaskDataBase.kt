package midis.example.task_manager.model.room.history_task

import androidx.room.Database
import androidx.room.RoomDatabase
import midis.example.task_manager.model.data.TaskData

@Database(entities = [TaskData::class], version = 1, exportSchema = false)
abstract class TaskDataBase : RoomDatabase() {
    abstract fun historyDao(): TaskDao
}