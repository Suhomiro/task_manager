package midis.example.task_manager.ui.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import midis.example.task_manager.model.data.DayData
import midis.example.task_manager.model.data.TaskData
import midis.example.task_manager.ui.main_screen.interactors.MainInteractorsImpl

class MainViewModel(
    private val mainInteractorsImpl: MainInteractorsImpl
): ViewModel() {
    private val liveDataForViewToObserve: MutableLiveData<List<DayData>> = MutableLiveData()

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

    fun saveTaskData(dayData: DayData) {
        cancelJob()
        viewModelCoroutineScope.launch { startInteractorSaveTaskData(dayData = dayData) }
    }

    private suspend fun startInteractorGetTaskData() =
        withContext(Dispatchers.IO) {
            liveDataForViewToObserve.postValue(
                mainInteractorsImpl.getTaskData()
            )
        }

    private suspend fun startInteractorSaveTaskData(dayData: DayData) =
        withContext(Dispatchers.IO) {
                mainInteractorsImpl.saveTaskData(dayData = dayData)
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
