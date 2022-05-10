package midis.example.task_manager.ui.main_screen.interactors

import midis.example.task_manager.model.data.DayData
import midis.example.task_manager.model.data.TaskData
import midis.example.task_manager.model.repository.task_data_repository.TaskRepository

class MainInteractorsImpl(
    private val taskRepository: TaskRepository
): MainInteractors {

    override suspend fun getTaskData(): List<DayData> =
        taskRepository.getTaskData()

    override suspend fun saveTaskData(dayData: DayData) {
        taskRepository.saveTaskData(dayData = dayData)
    }

    override suspend fun updateTaskData(dayData: DayData) {
        taskRepository.updateTaskData(dayData = dayData)
    }
}