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
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import me.thanish.prayers.R
import me.thanish.prayers.domain.PrayerTime
import me.thanish.prayers.domain.PrayerTimeCity
import me.thanish.prayers.domain.PrayerTimeType
import java.time.LocalDateTime

@Composable
fun WidgetContent(prayerTime: PrayerTime) {
    val context = LocalContext.current
    val bgColor = GlanceTheme.colors.surface.getColor(context).copy(alpha = 0.5f)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically,
        modifier = GlanceModifier
            .fillMaxSize()
            .background(bgColor)
            .padding(12.dp)
    ) {
        Text(
            text = context.getString(R.string.next_prayer_widget_name),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = GlanceTheme.colors.onSurface
            )
        )
        Text(
            text = prayerTime.getTimeString(),
            style = TextStyle(
                fontSize = 20.sp,
                color = GlanceTheme.colors.onSurface
            )
        )
        Text(
            text = prayerTime.type.getLabel(context),
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = GlanceTheme.colors.onSurface
            )
        )
    }
}

@Composable
@OptIn(ExperimentalGlancePreviewApi::class)
@Preview(widthDp = 100, heightDp = 120)
fun WidgetContentPreview() {
    val prayerTime = PrayerTime(
        city = PrayerTimeCity.colombo,
        type = PrayerTimeType.maghrib,
        time = LocalDateTime.of(2025, 1, 1, 12, 43)
    )

    GlanceTheme(GlanceTheme.colors) {
        WidgetContent(prayerTime)
    }
}
