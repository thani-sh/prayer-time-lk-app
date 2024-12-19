package me.thanish.prayers.routes.settings.components


import androidx.compose.runtime.Composable


@Composable
fun SettingsRouteContent(
    city: String,
    onCityChange: (String) -> Unit,
    methodology: String,
    onMethodologyChange: (String) -> Unit,
    notifyBefore: Int,
    onNotifyBeforeChange: (Int) -> Unit
) {
    SelectCityDropdown(city, onCityChange)
    SelectMethodologyDropdown(methodology, onMethodologyChange)
    SelectNotifyBefore(notifyBefore, onNotifyBeforeChange)
}
