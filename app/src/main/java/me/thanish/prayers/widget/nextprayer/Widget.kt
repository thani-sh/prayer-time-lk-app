package me.thanish.prayers.widget.nextprayer

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import me.thanish.prayers.domain.PrayerTime
import me.thanish.prayers.domain.PrayerTimeCity

class Widget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val city by remember { mutableStateOf(PrayerTimeCity.get(context)) }
            val prayerTime = PrayerTime.getNext(LocalContext.current, city, 1).firstOrNull()
                ?: return@provideContent

            GlanceTheme(GlanceTheme.colors) {
                WidgetContent(prayerTime)
            }
        }
    }
}
