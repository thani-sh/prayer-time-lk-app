package me.thanish.prayers.domain

import android.content.Context
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * PrayerTime is a data class representing a prayer time.
 */
data class PrayerTime(
    val city: PrayerTimeCity,
    val type: PrayerTimeType,
    val time: LocalDateTime
) {
    /**
     * getIntId returns a unique integer id for the prayer time.
     */
    fun getIntId(): Int {
        return getStringId().hashCode()
    }

    /**
     * getStringId returns a unique string id for the prayer time.
     */
    fun getStringId(): String {
        return "${city}|${type}|${time}"
    }

    /**
     * getEpochSeconds returns the epoch seconds for the prayer time.
     */
    fun getEpochSeconds(): Int {
        val zone = ZoneId.systemDefault()
        return time.atZone(zone).toInstant().epochSecond.toInt()
    }

    /**
     * getEpochMilli returns the epoch milli seconds for the prayer time.
     */
    fun getEpochMilli(): Long {
        val zone = ZoneId.systemDefault()
        return time.atZone(zone).toInstant().toEpochMilli()
    }

    /**
     * getTimeString returns the time string for the prayer time without date.
     */
    fun getTimeString(): String {
        return time.toLocalTime().toString()
    }

    /**
     * isCurrentPrayer returns true if the prayer time is the current prayer time.
     */
    fun isCurrentPrayer(): Boolean {
        val now = LocalDateTime.now()
        return now > time && now.plusSeconds(ACTIVE_DURATION_SECONDS) < time
    }

    /**
     * isNextPrayer returns true if the prayer time is the next prayer time.
     */
    fun isNextPrayer(context: Context): Boolean {
        return getNext(context, city, 1).firstOrNull() == this
    }

    /**
     * ✄ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    companion object {
        /**
         * ACTIVE_DURATION_SECONDS is the duration to consider a prayer as current.
         */
        const val ACTIVE_DURATION_SECONDS: Long = 30 * 60

        /**
         * fromStringId creates a prayer time from a string id.
         */
        fun fromStringId(id: String): PrayerTime? {
            val parts = id.split("|")
            if (parts.size != 3) {
                return null
            }
            val city = PrayerTimeCity.valueOf(parts[0])
            val type = PrayerTimeType.valueOf(parts[1])
            val time = LocalDateTime.parse(parts[2])
            return PrayerTime(city, type, time)
        }

        /**
         * getNext returns the next N prayer times starting from the given datetime.
         */
        fun getNext(
            context: Context,
            city: PrayerTimeCity,
            count: Int,
            from: LocalDateTime = LocalDateTime.now()
        ): List<PrayerTime> {
            val list = mutableListOf<PrayerTime>()
            var date = from.toLocalDate()
            while (list.size < count) {
                val prayerTimes = PrayerTimeTable.forDate(context, city, date).toList()
                for (prayerTime in prayerTimes) {
                    if (prayerTime.time.isBefore(from)) {
                        continue
                    }
                    list.add(prayerTime)
                }
                date = date.plusDays(1)
            }
            return list.take(count)
        }
    }
}
