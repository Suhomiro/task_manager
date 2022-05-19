package midis.example.task_manager.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_data")
data class TaskData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long,
    @ColumnInfo(name = "current_time")
    var currentTime: String,
    @ColumnInfo(name = "task_data")
    var taskData: String
        )

fun getListTaskData() =
    mutableListOf(TaskData(0, "", ""))

fun getTaskData() =
    TaskData(0, "", "")

