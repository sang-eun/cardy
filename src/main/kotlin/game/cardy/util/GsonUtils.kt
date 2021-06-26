package game.cardy.util

import com.google.gson.Gson

class GsonUtils {
    companion object {
        private val gson = Gson()

        fun toJson(obj: Any): String {
            return gson.toJson(obj)
        }

        fun <T> fromJson(clazz: Class<T>, obj: String): T {
            return gson.fromJson(obj, clazz)
        }
    }
}
