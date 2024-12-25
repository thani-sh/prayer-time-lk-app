package me.thanish.prayers.routes.settings

import android.Manifest.permission.POST_NOTIFICATIONS
import android.Manifest.permission.USE_EXACT_ALARM
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import me.thanish.prayers.R
import me.thanish.prayers.device.RequestPermission
import me.thanish.prayers.domain.NotificationOffset
import me.thanish.prayers.domain.PrayerTimeCity
import me.thanish.prayers.domain.PrayerTimeMethod
import me.thanish.prayers.routes.RouteSpec
import me.thanish.prayers.routes.RouteType
import me.thanish.prayers.routes.settings.components.SettingsRouteContent
import me.thanish.prayers.theme.PrayersTheme
import me.thanish.prayers.worker.SchedulerWorker

/**
 * Describes the route to use with navigation.
 */
val SettingsRouteSpec = RouteSpec(
    name = "settings",
    text = R.string.route_settings_name,
    type = RouteType.SECONDARY,
    icon = { Pair(Icons.Filled.Settings, Icons.Outlined.Settings) },
    content = { nav: NavController, modifier: Modifier -> SettingsRoute(nav, modifier) }
)

@Composable
fun SettingsRoute(nav: NavController, modifier: Modifier = Modifier) {
    var city by remember { mutableStateOf(PrayerTimeCity.get()) }
    var method by remember { mutableStateOf(PrayerTimeMethod.get()) }
    var offset by remember { mutableStateOf(NotificationOffset.get()) }

    val onCityChange = { selectedCity: PrayerTimeCity ->
        city = selectedCity
        PrayerTimeCity.set(selectedCity)
        if (NotificationOffset.isEnabled()) {
            SchedulerWorker.schedule(nav.context, city)
        }
    }

    val onMethodChange = { selectedMethod: PrayerTimeMethod ->
        method = selectedMethod
        PrayerTimeMethod.set(selectedMethod)
        if (NotificationOffset.isEnabled()) {
            SchedulerWorker.schedule(nav.context, city)
        }
    }

    val onOffsetChange = { selectedOffset: NotificationOffset ->
        offset = selectedOffset
        NotificationOffset.set(selectedOffset)
        if (NotificationOffset.isEnabled()) {
            SchedulerWorker.schedule(nav.context, city)
        }
    }

    if (NotificationOffset.isEnabled()) {
        RequestPermission(
            requestedPermissions = arrayOf(POST_NOTIFICATIONS, USE_EXACT_ALARM),
            handleSuccess = { },
            handleFailure = { }
        )
    }

    SettingsRouteView(
        city,
        onCityChange,
        method,
        onMethodChange,
        offset,
        onOffsetChange,
        modifier
    )
}

@Composable
fun SettingsRouteView(
    city: PrayerTimeCity,
    onCityChange: (PrayerTimeCity) -> Unit,
    method: PrayerTimeMethod,
    onMethodChange: (PrayerTimeMethod) -> Unit,
    offset: NotificationOffset,
    onOffsetChange: (NotificationOffset) -> Unit,
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
                    method,
                    onMethodChange,
                    offset,
                    onOffsetChange
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun SettingsRoutePreview() {
    val city = PrayerTimeCity.colombo
    val method = PrayerTimeMethod.shafi
    val offset = NotificationOffset(10)

    PrayersTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SettingsRouteView(
                city,
                {},
                method,
                {},
                offset,
                {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
