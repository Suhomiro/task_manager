package midis.example.task_manager.model.repository.task_data_repository

import midis.example.task_manager.model.data.TaskData
import midis.example.task_manager.model.data_source.task_data_source.TaskDataSource

class TaskRepositoryImpl(
    private val taskDataSource: TaskDataSource
): TaskRepository {

    override suspend fun getTaskData(): List<TaskData> =
        taskDataSource.getTaskData()

    override suspend fun saveTaskData(taskData: TaskData) {
        taskDataSource.saveTaskData(taskData = taskData)
    }

    override suspend fun updateTaskData(taskData: TaskData) {
        taskDataSource.updateTaskData(taskData = taskData)
    }

}