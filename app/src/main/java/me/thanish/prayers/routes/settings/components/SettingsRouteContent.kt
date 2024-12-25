package me.thanish.prayers.routes.settings.components


import androidx.compose.runtime.Composable
import me.thanish.prayers.domain.NotificationOffset
import me.thanish.prayers.domain.PrayerTimeCity
import me.thanish.prayers.domain.PrayerTimeMethod


@Composable
fun SettingsRouteContent(
    city: PrayerTimeCity,
    onCityChange: (PrayerTimeCity) -> Unit,
    method: PrayerTimeMethod,
    onMethodChange: (PrayerTimeMethod) -> Unit,
    offset: NotificationOffset,
    onOffsetChange: (NotificationOffset) -> Unit
) {
    SelectCityDropdown(city, onCityChange)
    SelectMethodDropdown(method, onMethodChange)
    SelectOffsetDropdown(offset, onOffsetChange)
}
