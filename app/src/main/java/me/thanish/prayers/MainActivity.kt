package me.thanish.prayers

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.tencent.mmkv.MMKV
import me.thanish.prayers.ui.theme.PrayersTheme
import me.thanish.prayers.worker.NotificationWorker
import me.thanish.prayers.worker.SchedulerWorker

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MMKV.initialize(applicationContext)
        SchedulerWorker.initialize(applicationContext)
        NotificationWorker.initialize(applicationContext)

        enableEdgeToEdge()
        setContent {
            PrayersTheme {
                Navigation()
            }
        }
    }
}
