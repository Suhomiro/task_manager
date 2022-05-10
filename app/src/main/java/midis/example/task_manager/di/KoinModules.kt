package midis.example.task_manager.di

import androidx.room.Room
import midis.example.task_manager.model.data_source.task_data_source.TaskDataSourceImpl
import midis.example.task_manager.model.repository.task_data_repository.TaskRepository
import midis.example.task_manager.model.repository.task_data_repository.TaskRepositoryImpl
import midis.example.task_manager.model.room.history_task.HistoryTaskDataBase
import midis.example.task_manager.ui.main_screen.MainViewModel
import midis.example.task_manager.ui.main_screen.interactors.MainInteractorsImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryTaskDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryTaskDataBase>().historyDao() }



    single<TaskRepository> (named(TASK_HISTORY)) {
        TaskRepositoryImpl(
            TaskDataSourceImpl(get())
        )
    }

}
val mainScreen = module {

    single {
        MainInteractorsImpl(
            taskRepository = get(qualifier = named(TASK_HISTORY))
        )
    }

    viewModel {
        MainViewModel(mainInteractorsImpl = get())
    }
}