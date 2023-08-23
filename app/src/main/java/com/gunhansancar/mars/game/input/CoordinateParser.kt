package com.gunhansancar.mars.game.input

import com.gunhansancar.mars.game.Constants

/**
 * Parses a coordinate integer value which cannot be more than [Constants.BoardMaxSize]
 */
class CoordinateParser {
    fun parse(input: String): Int? {
        val x = input.toIntOrNull()

        if (x == null || x < 0 || x > Constants.BoardMaxSize) {
            return null
        }

        return x
    }
}