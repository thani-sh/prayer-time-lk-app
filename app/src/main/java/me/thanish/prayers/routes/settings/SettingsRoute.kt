package me.thanish.prayers.routes.settings

import android.Manifest.permission.POST_NOTIFICATIONS
import android.Manifest.permission.USE_EXACT_ALARM
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import me.thanish.prayers.router.RouteSpec
import me.thanish.prayers.router.RouteType
import me.thanish.prayers.routes.settings.components.SettingsRouteContent
import me.thanish.prayers.states.Preferences
import me.thanish.prayers.states.RequestPermission
import me.thanish.prayers.ui.theme.PrayersTheme
import me.thanish.prayers.worker.SchedulerWorker

/**
 * Describes the route to use with navigation.
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
val SettingsRouteSpec = RouteSpec(
    name = "settings",
    text = "Settings",
    type = RouteType.SECONDARY,
    icon = { Pair(Icons.Filled.Settings, Icons.Outlined.Settings) },
    content = { nav: NavController, modifier: Modifier -> SettingsRoute(nav, modifier) }
)

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SettingsRoute(nav: NavController, modifier: Modifier = Modifier) {
    var city by remember { mutableStateOf(Preferences.getCity()) }
    var methodology by remember { mutableStateOf(Preferences.getMethodology()) }
    var notifyBefore by remember { mutableIntStateOf(Preferences.getNotifyBefore()) }

    val onCityChange = { selectedCity: String ->
        city = selectedCity
        Preferences.setCity(selectedCity)
        SchedulerWorker.schedule(nav.context, selectedCity)
    }

    val onMethodologyChange = { selectedMethodology: String ->
        methodology = selectedMethodology
        Preferences.setMethodology(selectedMethodology)
        SchedulerWorker.schedule(nav.context, city)
    }

    val onNotifyBeforeChange = { selectedNotifyBefore: Int ->
        notifyBefore = selectedNotifyBefore
        Preferences.setNotifyBefore(selectedNotifyBefore)
        if (Preferences.getNotifyEnabled()) {
            SchedulerWorker.schedule(nav.context, city)
        }
    }

    if (Preferences.getNotifyEnabled()) {
        RequestPermission(
            requestedPermissions = arrayOf(POST_NOTIFICATIONS, USE_EXACT_ALARM),
            handleSuccess = { },
            handleFailure = { }
        )
    }

    SettingsRouteView(
        city,
        onCityChange,
        methodology,
        onMethodologyChange,
        notifyBefore,
        onNotifyBeforeChange,
        modifier
    )
}

@Composable
fun SettingsRouteView(
    city: String,
    onCityChange: (String) -> Unit,
    methodology: String,
    onMethodologyChange: (String) -> Unit,
    notifyBefore: Int,
    onNotifyBeforeChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SettingsRouteContent(
                    city,
                    onCityChange,
                    methodology,
                    onMethodologyChange,
                    notifyBefore,
                    onNotifyBeforeChange
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun SettingsRoutePreview() {
    val city = "Colombo"
    val methodology = "Shafi"
    val notifyBefore = 1000 * 60 * 10

    PrayersTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SettingsRouteView(
                city,
                {},
                methodology,
                {},
                notifyBefore,
                {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
