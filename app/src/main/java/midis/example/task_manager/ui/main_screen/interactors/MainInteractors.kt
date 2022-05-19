package midis.example.task_manager.ui.main_screen.interactors

import midis.example.task_manager.model.data.TaskData

interface MainInteractors{
    suspend fun getTaskData(): List<TaskData>
    suspend fun saveTaskData(taskData: TaskData)
    suspend fun updateTaskData(taskData: TaskData)
}