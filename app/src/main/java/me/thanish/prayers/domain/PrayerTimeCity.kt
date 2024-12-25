package me.thanish.prayers.domain

import android.content.Context
import com.tencent.mmkv.MMKV
import me.thanish.prayers.R

/**
 * PrayerTimeCity is the city of a prayer time.
 */
enum class PrayerTimeCity(private val key: Int) {
    colombo(R.string.prayers_city_colombo);

    /**
     * getLabel returns the name of the city with i18n.
     */
    fun getLabel(context: Context): String {
        return context.getString(key)
    }

    /**
     * ✄ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     */

    companion object {
        /**
         * STORE_KEY is the MMKV key for storing the current prayer city.
         */
        private const val STORE_KEY = "PrayerTimeCity"

        /**
         * store is the MMKV instance for storing the current prayer city.
         */
        private val store = MMKV.defaultMMKV();

        /**
         * set sets the current prayer city.
         */
        fun set(city: PrayerTimeCity) {
            store.putInt(STORE_KEY, city.ordinal)
        }

        /**
         * get returns the current prayer city.
         */
        fun get(): PrayerTimeCity {
            val index = store.getInt(STORE_KEY, colombo.ordinal)
            return entries[index]
        }
    }
}
