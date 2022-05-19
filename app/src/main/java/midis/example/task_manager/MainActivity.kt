package midis.example.task_manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import midis.example.task_manager.databinding.ActivityMainBinding
import midis.example.task_manager.ui.main_screen.MainFragment

class MainActivity : AppCompatActivity() {

    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
    }
}