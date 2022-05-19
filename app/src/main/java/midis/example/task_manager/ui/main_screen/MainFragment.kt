package midis.example.task_manager.ui.main_screen

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import midis.example.task_manager.databinding.FragmentMainBinding
import midis.example.task_manager.model.data.TaskData
import midis.example.task_manager.model.data.getTaskData
import midis.example.task_manager.ui.main_screen.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalTime
import java.util.*


class MainFragment: Fragment() {

    companion object {
        private const val REQUEST_CODE_STT = 1
    }

    private lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var mainAdapter: MainAdapter? = null
    private var taskData: TaskData = getTaskData()
    private var newTaskDataList: MutableList<TaskData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initViewModel()
        btnInit()
        return root

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            // Handle the result for our request code.
            REQUEST_CODE_STT -> {
                // Safety checks to ensure data is available.
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Retrieve the result array.
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    // Ensure result array is not null or empty to avoid errors.
                    if (!result.isNullOrEmpty()) {
                        // Recognized text is in the first position.
                        val recognizedText = result[0]
                        // Do what you want with the recognized text.
                        taskData.currentTime = LocalTime.now().toString()
                        taskData.taskData = recognizedText
                        mainViewModel.saveTaskData(taskData)
                        newTaskDataList += taskData
                        mainAdapter?.setNewData(newTaskDataList)
                    }
                }
            }
        }
    }

    private fun initViewModel(){

        if (binding.recyclerViewMainScreen.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by viewModel()
        mainViewModel = viewModel
        val observer = Observer<List<TaskData>> { renderData(it) }
        mainViewModel.getTaskData()
        mainViewModel.getData().observe(viewLifecycleOwner, observer)
    }

    private fun renderData(taskDataList: List<TaskData>){
            mainAdapter = MainAdapter()
            mainAdapter?.setData(taskDataList.toMutableList())
            for(task in taskDataList){
                newTaskDataList += task
            }
            binding.recyclerViewMainScreen.layoutManager = LinearLayoutManager(activity)
            binding.recyclerViewMainScreen.adapter = mainAdapter
    }

    private fun btnInit(){
        binding.fabMicrophone.setOnClickListener {
            // Get the Intent action
            val sttIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            // Language model defines the purpose, there are special models for other use cases, like search.
            sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            // Adding an extra language, you can use any language from the Locale class.
            sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            // Text that shows up on the Speech input prompt.
            sttIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now!")
            try {
                // Start the intent for a result, and pass in our request code.
                startActivityForResult(sttIntent, REQUEST_CODE_STT)
            } catch (e: ActivityNotFoundException) {
                // Handling error when the service is not available.
                e.printStackTrace()
                Toast.makeText(context, "Your device does not support STT.", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}