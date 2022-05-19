package midis.example.task_manager.model.data_source.task_data_source

import midis.example.task_manager.model.data.TaskData

interface TaskDataSource {

    suspend fun getTaskData(): List<TaskData>
    suspend fun saveTaskData(taskData: TaskData)
    suspend fun updateTaskData(taskData: TaskData)
}