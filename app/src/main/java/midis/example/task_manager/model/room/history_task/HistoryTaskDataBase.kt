package midis.example.task_manager.model.room.history_task

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import midis.example.task_manager.model.data.DayData

@Database(entities = [DayData::class], version = 1, exportSchema = false)
@TypeConverters(Convertors::class)
abstract class HistoryTaskDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryTaskDao
}