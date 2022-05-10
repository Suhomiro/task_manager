package midis.example.task_manager.model.room.history_task

import androidx.room.TypeConverter
import com.google.gson.Gson
import midis.example.task_manager.model.data.TaskData

class Convertors {
    @TypeConverter
    fun listToJsonString(value: List<TaskData>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<TaskData>::class.java).toList()
}