package game.cardy.util

import com.google.gson.Gson

class GsonUtils {
    companion object {
        private val gson = Gson()

        fun toJson(obj: Any): String {
            return gson.toJson(obj)
        }

        fun <T> fromJson(obj: String, clazz: Class<T>): T {
            return gson.fromJson(obj, clazz)
        }
    }
}
