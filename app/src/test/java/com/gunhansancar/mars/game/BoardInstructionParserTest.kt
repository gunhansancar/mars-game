package com.gunhansancar.mars.game

import com.gunhansancar.mars.game.input.BoardInstruction
import com.gunhansancar.mars.game.input.BoardInstructionParser
import org.junit.Assert.assertEquals
import org.junit.Test

class BoardInstructionParserTest {
    private val parser = BoardInstructionParser()

    @Test
    fun test_parse() {
        listOf(
            Pair("5 3", BoardInstruction(5, 3)),
            Pair("37", null),
            Pair("55 3", null),
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