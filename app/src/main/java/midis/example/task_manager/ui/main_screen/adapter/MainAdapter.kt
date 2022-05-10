package midis.example.task_manager.ui.main_screen.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import midis.example.task_manager.databinding.MainScreenItemBinding
import midis.example.task_manager.model.data.TaskData
import midis.example.task_manager.model.data.getTaskData

class MainAdapter() : RecyclerView.Adapter<MainAdapter.MainItemViewHolder>() {

    private var taskData = getTaskData()
    private var _binding: MainScreenItemBinding? = null
    private val binding get() = _binding!!
    private var alertDialog: AlertDialog? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<TaskData>) {
        this.taskData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder {
        _binding = MainScreenItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainItemViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        with(taskData!![position]) {
            binding.textViewTask.text = task
            binding.textViewCurrentTime.text = currentTime
        }
    }


    override fun getItemCount(): Int {
        return taskData?.size ?: 0
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        _binding = null
    }


    inner class MainItemViewHolder(binding: MainScreenItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        }

}

