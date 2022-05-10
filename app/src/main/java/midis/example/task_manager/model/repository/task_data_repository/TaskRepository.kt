package midis.example.task_manager.model.repository.task_data_repository

import midis.example.task_manager.model.data.DayData
import midis.example.task_manager.model.data.TaskData

interface TaskRepository {

    suspend fun getTaskData(): List<DayData>
    suspend fun saveTaskData(dayData: DayData)
    suspend fun updateTaskData(dayData: DayData)
}