package midis.example.task_manager.ui.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import midis.example.task_manager.model.data.TaskData
import midis.example.task_manager.ui.main_screen.interactors.MainInteractorsImpl

class MainViewModel(
    private val mainInteractorsImpl: MainInteractorsImpl
): ViewModel() {
    private val liveDataForViewToObserve: MutableLiveData<List<TaskData>> = MutableLiveData()

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    fun getData() = liveDataForViewToObserve

    fun getTaskData() {
        cancelJob()
        viewModelCoroutineScope.launch { startInteractorGetTaskData() }
    }

    fun saveTaskData(taskData: TaskData) {
        cancelJob()
        viewModelCoroutineScope.launch { startInteractorSaveTaskData(taskData = taskData) }
    }

    fun updateTaskData(taskData: TaskData){
        cancelJob()
        viewModelCoroutineScope.launch { startInteractorUpdateTaskData(taskData = taskData) }
    }

    private suspend fun startInteractorGetTaskData() =
        withContext(Dispatchers.IO) {
            liveDataForViewToObserve.postValue(
                mainInteractorsImpl.getTaskData()
            )
        }

    private suspend fun startInteractorSaveTaskData(taskData: TaskData) =
        withContext(Dispatchers.IO) {
                mainInteractorsImpl.saveTaskData(taskData = taskData)
        }

    private suspend fun startInteractorUpdateTaskData(taskData: TaskData) =
        withContext(Dispatchers.IO) {
            mainInteractorsImpl.updateTaskData(taskData = taskData)
        }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    private fun handleError(error: Throwable) {
        throw error
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }
}
