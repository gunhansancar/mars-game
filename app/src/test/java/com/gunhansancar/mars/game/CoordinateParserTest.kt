package com.gunhansancar.mars.game

import com.gunhansancar.mars.game.input.CoordinateParser
import org.junit.Assert.assertEquals
import org.junit.Test

class CoordinateParserTest {
    private val parser = CoordinateParser()

    @Test
    fun test_parse() {
        listOf<Pair<String, Any?>>(
            Pair("5", 5),
            Pair("37", 37),
            Pair("50", Constants.BoardMaxSize),
            Pair("55", null),
            Pair("-10", null),
            Pair("ABC", null),
            Pair("_xyz", null),
        ).forEach { testParse(it) }
    }

    private fun testParse(input: Pair<String, Any?>) {
        val result = parser.parse(input.first)

        assertEquals(input.second, result)
    }
}