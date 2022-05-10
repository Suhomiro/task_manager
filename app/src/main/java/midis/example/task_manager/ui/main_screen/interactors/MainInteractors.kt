package midis.example.task_manager.ui.main_screen.interactors

import midis.example.task_manager.model.data.DayData
import midis.example.task_manager.model.data.TaskData

interface MainInteractors{
    suspend fun getTaskData(): List<DayData>
    suspend fun saveTaskData(dayData: DayData)
    suspend fun updateTaskData(dayData: DayData)
}