package me.thanish.prayers.states

import com.tencent.mmkv.MMKV;

/**
 * use mmkv to persist user preferences
 */
private val store = MMKV.defaultMMKV();

/**
 * data class to store user preferences
 */
data class Preferences(val city: String) {
    companion object {
        internal const val DEFAULT_CITY = "Colombo"
        internal const val DEFAULT_METHODOLOGY = "Shafi"
        internal const val DEFAULT_NOTIFY_BEFORE = -1
        internal const val DISABLED_NOTIFY_BEFORE = -1

        /**
         * Get the selected city
         */
        fun getCity(): String {
            return store.getString("city", DEFAULT_CITY) as String
        }

        /**
         * Set the selected city
         */
        fun setCity(city: String) {
            store.putString("city", city)
        }

        /**
         * Get the selected methodology
         */
        fun getMethodology(): String {
            return store.getString("methodology", DEFAULT_METHODOLOGY) as String
        }

        /**
         * Set the selected methodology
         */
        fun setMethodology(methodology: String) {
            store.putString("methodology", methodology)
        }

        /**
         * Get the notify enabled state
         */
        fun getNotifyEnabled(): Boolean {
            return getNotifyBefore() != DISABLED_NOTIFY_BEFORE
        }

        /**
         * Get the notify before state
         */
        fun getNotifyBefore(): Int {
            return store.getInt("notify-before", DEFAULT_NOTIFY_BEFORE)
        }

        /**
         * Set the notify before state
         */
        fun setNotifyBefore(before: Int) {
            store.putInt("notify-before", before)
        }
    }
}
