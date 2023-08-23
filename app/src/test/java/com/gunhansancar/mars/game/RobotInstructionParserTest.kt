package com.gunhansancar.mars.game

import com.gunhansancar.mars.game.input.Command
import com.gunhansancar.mars.game.input.Orientation
import com.gunhansancar.mars.game.input.Position
import com.gunhansancar.mars.game.input.RobotInstruction
import com.gunhansancar.mars.game.input.RobotInstructionParser
import org.junit.Assert.assertEquals
import org.junit.Test

class RobotInstructionParserTest {
    private val parser = RobotInstructionParser()

    @Test
    fun test_parse() {
        listOf(
            Pair(
                listOf("10 30 E", "RFRFRF"),
                listOf(
                    RobotInstruction(
                        Position(10, 30, Orientation.East),
                        listOf(
                            Command.Right,
                            Command.Forward,
                            Command.Right,
                            Command.Forward,
                            Command.Right,
                            Command.Forward,
                        )
                    )
                )
            ),
            Pair(
                listOf("10 30 E", "RFRFRF","\n", "3 2 N", "FRRFLL"),
                listOf(
                    RobotInstruction(
                        Position(10, 30, Orientation.East),
                        listOf(
                            Command.Right,
                            Command.Forward,
                            Command.Right,
                            Command.Forward,
                            Command.Right,
                            Command.Forward,
                        )
                    ),
                    RobotInstruction(
                        Position(3, 2, Orientation.North),
                        listOf(
                            Command.Forward,
                            Command.Right,
                            Command.Right,
                            Command.Forward,
                            Command.Left,
                            Command.Left,
                        )
                    )
                )
            ),
            Pair(
                listOf("10 30 E"),
                emptyList()
            ),
            Pair(emptyList(), emptyList()),
        ).forEach { testParse(it) }
    }

    private fun testParse(input: Pair<List<String>, Any?>) {
        val result = parser.parse(input.first)

        assertEquals(input.second, result)
    }
}