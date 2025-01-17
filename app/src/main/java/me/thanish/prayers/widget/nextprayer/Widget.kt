package me.thanish.prayers.widget.nextprayer

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import me.thanish.prayers.domain.PrayerTime

class Widget : GlanceAppWidget() {
    /**
     * stateDefinition is used to store the state of the widget. This affects
     * when the widget is updated and new data gets rendered on the widget.
     */
    override val stateDefinition: GlanceStateDefinition<PrayerTime> get() = WidgetState()

    /**
     * provideGlance is called when the widget is created and maybe after a long time interval.
     */
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val prayerTime = currentState<PrayerTime>()

            GlanceTheme(GlanceTheme.colors) {
                WidgetContent(prayerTime)
            }
        }
    }
}
