package midis.example.task_manager.ui.main_screen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import midis.example.task_manager.databinding.MainScreenItemBinding
import midis.example.task_manager.model.data.TaskData
import midis.example.task_manager.model.data.getListTaskData
import midis.example.task_manager.utils.TaskDiffUtilsCallback

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainItemViewHolder>() {

    private var taskDataList = getListTaskData()
    private var _binding: MainScreenItemBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<TaskData>) {
        this.taskDataList = data
        notifyDataSetChanged()
    }

    fun setNewData(newTaskDataList: List<TaskData>) {
        val diffCallback = TaskDiffUtilsCallback(
            oldList = taskDataList,
            newList = newTaskDataList
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        taskDataList.clear()
        taskDataList.addAll(newTaskDataList)
        diffResult.dispatchUpdatesTo(this@MainAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder {
        _binding = MainScreenItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainItemViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        holder.bind(taskDataList[position])
    }

    override fun getItemCount(): Int =
        taskDataList.size

    inner class MainItemViewHolder(binding: MainScreenItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: TaskData) {
            with (binding) {
                textViewTask.text = data.taskData
                textViewCurrentTime.text = data.currentTime
                }
            }
        }

}

