package co.akukushkin.findstackusers.util

import java.text.SimpleDateFormat
import java.util.*


class DateFormatUtils {
    companion object {
        private const val DATE_FORMAT = "dd/MM/yyyy"

        fun parseDate(date:Date):String{
            return SimpleDateFormat(DATE_FORMAT,Locale.UK).format(date)
        }
    }
}