package me.thanish.prayers.widget.nextprayer

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import me.thanish.prayers.domain.PrayerTime
import me.thanish.prayers.domain.PrayerTimeCity
import me.thanish.prayers.domain.PrayerTimeTable

@Composable
fun WidgetContent(prayerTime: PrayerTime) {
    val context = LocalContext.current

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(GlanceTheme.colors.surface)
            .padding(12.dp)
    ) {
        Spacer(GlanceModifier.defaultWeight())
        Column(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = prayerTime.type.getLabel(context),
                style = TextStyle(
                    fontSize = 15.sp,
                    color = GlanceTheme.colors.onSurface
                )
            )
            Text(
                text = prayerTime.getTimeString(),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = GlanceTheme.colors.onSurface
                )
            )
            Text(
                text = prayerTime.getUntilString(),
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    color = GlanceTheme.colors.onSurface
                ),
            )
        }
        Spacer(GlanceModifier.defaultWeight())
    }
}

@Composable
@OptIn(ExperimentalGlancePreviewApi::class)
@Preview(widthDp = 80, heightDp = 100)
fun WidgetContentAsrPreview() {
    val times = PrayerTimeTable.forToday(LocalContext.current, PrayerTimeCity.colombo)

    GlanceTheme(GlanceTheme.colors) {
        WidgetContent(times.asr)
    }
}

@Composable
@OptIn(ExperimentalGlancePreviewApi::class)
@Preview(widthDp = 80, heightDp = 100)
fun WidgetContentMaghribPreview() {
    val times = PrayerTimeTable.forToday(LocalContext.current, PrayerTimeCity.colombo)

    GlanceTheme(GlanceTheme.colors) {
        WidgetContent(times.maghrib)
    }
}
