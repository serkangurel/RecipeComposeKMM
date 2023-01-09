package com.serkangurel.recipecomposekmm.util

import kotlinx.datetime.*

class DatetimeUtil {

    fun now(): LocalDateTime {
        val currentMoment: Instant = Clock.System.now()
        return currentMoment.toLocalDateTime(TimeZone.UTC)
    }

    fun toLocalDate(date: Long): LocalDateTime {
        return Instant.fromEpochMilliseconds(date).toLocalDateTime(TimeZone.UTC)
    }

    fun toEpochMilliseconds(date: LocalDateTime): Long {
        return date.toInstant(TimeZone.UTC).toEpochMilliseconds()
    }
}






