package midis.example.task_manager.model.repository.task_data_repository

import midis.example.task_manager.model.data.DayData
import midis.example.task_manager.model.data.TaskData
import midis.example.task_manager.model.data_source.task_data_source.TaskDataSource

class TaskRepositoryImpl(
    private val taskDataSource: TaskDataSource
): TaskRepository {

    override suspend fun getTaskData(): List<DayData> =
        taskDataSource.getTaskData()

    override suspend fun saveTaskData(dayData: DayData) {
        taskDataSource.saveTaskData(dayData = dayData)
    }

    override suspend fun updateTaskData(dayData: DayData) {
        taskDataSource.updateTaskData(dayData = dayData)
    }

}