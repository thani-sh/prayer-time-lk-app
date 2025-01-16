package me.thanish.prayers.widget.nextprayer

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import me.thanish.prayers.R
import me.thanish.prayers.domain.PrayerTime
import me.thanish.prayers.domain.PrayerTimeCity

class Widget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme(GlanceTheme.colors) {
                Content()
            }
        }
    }


    @Composable
    fun Content() {
        val context = LocalContext.current
        val city by remember { mutableStateOf(PrayerTimeCity.get(context)) }
        val time = PrayerTime.getNext(context, city, 1).firstOrNull() ?: return

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically,
            modifier = GlanceModifier
                .fillMaxSize()
                .background(GlanceTheme.colors.background)
                .padding(8.dp)
        ) {
            Text(
                text = context.getString(R.string.next_prayer_widget_name),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 8.sp)
            )
            Text(
                text = time.getTimeString(),
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                text = time.type.getLabel(context),
                style = TextStyle(fontSize = 12.sp)
            )
        }
    }
}
