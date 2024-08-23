package com.thinknote.app.utilities

import kotlin.random.Random

object ColorCodes {

    private val COLORS = listOf(
        0xFFFFD8F4,
        0xFFB0E9CA,
        0xFFFDE99D,
        0xFFFFEADD,
        0xFFD9E8FC
    )

    fun generateColor(): Long {
        val index = Random.nextInt(COLORS.size)
        return COLORS[index]
    }
}