package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    fun getMinute(ms: Long): String {
        val min = abs(ms / 1000L / 60)
        if (min in 10..20) {
            return "$min минут"
        }
        return when (min.toString().last()) {
            '1' -> "$min минуту"
            '2', '3', '4' -> "$min минуты"
            else -> "$min минут"
        }
    }

    fun getHour(ms: Long): String {
        val hour = abs(ms / 1000L / 60 / 60)
        if (hour in 10..20) {
            return "$hour часов"
        }
        return when (hour.toString().last()) {
            '1' -> "$hour час"
            '2', '3', '4' -> "$hour часа"
            else -> "$hour часов"
        }
    }

    fun getDay(ms: Long): String {
        val day = abs(ms / 1000L / 60 / 60 / 24)
        if (day in 10..20) {
            return "$day дней"
        }
        return when (day.toString().last()) {
            '1' -> "$day день"
            '2', '3', '4' -> "$day дня"
            else -> "$day дней"
        }
    }

    return when (val diff = this.time - date.time) {
        in -1 * SECOND..1 * SECOND -> "только что"
        in -45 * SECOND..-1 * SECOND -> "несколько секунд назад"
        in 1 * SECOND..45 * SECOND -> "через несколько секунд"
        in -75 * SECOND..-45 * SECOND -> "минуту назад"
        in 45 * SECOND..75 * SECOND -> "через минуту"
        in -45 * MINUTE..-75 * SECOND -> "${getMinute(diff)} назад"
        in 75 * SECOND..45 * MINUTE -> "через ${getMinute(diff)}"
        in -75 * MINUTE..-45 * MINUTE -> "час назад"
        in 45 * MINUTE..75 * MINUTE -> "через час"
        in -22 * HOUR..-75 * MINUTE -> "${getHour(diff)} назад"
        in 75 * MINUTE..22 * HOUR -> "через ${getHour(diff)}"
        in -26 * HOUR..-22 * HOUR -> "день назад"
        in 22 * HOUR..26 * HOUR -> "через день"
        in -360 * DAY..-26 * HOUR -> "${getDay(diff)} назад"
        in 26 * HOUR..360 * DAY -> "через ${getDay(diff)}"
        in Long.MIN_VALUE..-360 * DAY -> "больше года назад"
        in 360 * DAY..Long.MAX_VALUE -> "больше, чем через год"
        else -> throw IllegalArgumentException("$diff")
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}