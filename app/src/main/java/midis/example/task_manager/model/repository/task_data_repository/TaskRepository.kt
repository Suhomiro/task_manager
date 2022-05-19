package midis.example.task_manager.model.repository.task_data_repository

import midis.example.task_manager.model.data.TaskData

interface TaskRepository {

    suspend fun getTaskData(): List<TaskData>
    suspend fun saveTaskData(taskData: TaskData)
    suspend fun updateTaskData(taskData: TaskData)
}