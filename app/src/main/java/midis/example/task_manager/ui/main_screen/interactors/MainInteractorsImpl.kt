package midis.example.task_manager.ui.main_screen.interactors

import midis.example.task_manager.model.data.TaskData
import midis.example.task_manager.model.repository.task_data_repository.TaskRepository

class MainInteractorsImpl(
    private val taskRepository: TaskRepository
): MainInteractors {

    override suspend fun getTaskData(): List<TaskData> =
        taskRepository.getTaskData()

    override suspend fun saveTaskData(taskData: TaskData) {
        taskRepository.saveTaskData(taskData = taskData)
    }

    override suspend fun updateTaskData(taskData: TaskData) {
        taskRepository.updateTaskData(taskData = taskData)
    }
}