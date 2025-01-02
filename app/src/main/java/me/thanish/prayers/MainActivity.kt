package me.thanish.prayers

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import me.thanish.prayers.theme.PrayersTheme
import me.thanish.prayers.worker.NotificationWorker
import me.thanish.prayers.worker.SchedulerWorker

/**
 * MainActivity is the main activity of the app.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
