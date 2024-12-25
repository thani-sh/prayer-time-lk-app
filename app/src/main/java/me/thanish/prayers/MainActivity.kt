package me.thanish.prayers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tencent.mmkv.MMKV
import me.thanish.prayers.theme.PrayersTheme
import me.thanish.prayers.worker.NotificationWorker
import me.thanish.prayers.worker.SchedulerWorker

class MainActivity : ComponentActivity() {
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
