package midis.example.task_manager.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day_data")
data class DayData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "task_data")
    val taskData: List<TaskData>?
        )

fun getTaskData(): MutableList<TaskData>? =
    DayData(0,"", null).taskData?.toMutableList()