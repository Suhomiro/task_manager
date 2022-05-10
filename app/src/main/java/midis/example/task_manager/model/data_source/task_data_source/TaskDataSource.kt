package midis.example.task_manager.model.data_source.task_data_source

import midis.example.task_manager.model.data.DayData
import midis.example.task_manager.model.data.TaskData

interface TaskDataSource {

    suspend fun getTaskData(): List<DayData>
    suspend fun saveTaskData(dayData: DayData)
    suspend fun updateTaskData(dayData: DayData)
}