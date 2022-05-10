package midis.example.task_manager.model.data_source.task_data_source

import midis.example.task_manager.model.data.DayData
import midis.example.task_manager.model.data.TaskData
import midis.example.task_manager.model.room.history_task.HistoryTaskDao

class TaskDataSourceImpl(
    private val historyTaskDao: HistoryTaskDao
): TaskDataSource {

    override suspend fun getTaskData(): List<DayData> =
        historyTaskDao.getTask()

    override suspend fun saveTaskData(dayData: DayData) {
        historyTaskDao.insert(dayData = dayData)
    }

    override suspend fun updateTaskData(dayData: DayData) {
        historyTaskDao.update(dayData = dayData)
    }

}