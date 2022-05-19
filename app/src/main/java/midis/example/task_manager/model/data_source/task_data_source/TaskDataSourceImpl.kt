package midis.example.task_manager.model.data_source.task_data_source

import midis.example.task_manager.model.data.TaskData
import midis.example.task_manager.model.room.history_task.TaskDao

class TaskDataSourceImpl(
    private val taskDao: TaskDao
): TaskDataSource {

    override suspend fun getTaskData(): List<TaskData> =
        taskDao.getTask()

    override suspend fun saveTaskData(taskData: TaskData) {
        taskDao.insert(taskData = taskData)
    }

    override suspend fun updateTaskData(taskData: TaskData) {
        taskDao.update(taskData = taskData)
    }

}