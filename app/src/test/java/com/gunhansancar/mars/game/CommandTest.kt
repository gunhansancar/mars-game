package com.gunhansancar.mars.game

import com.gunhansancar.mars.game.model.Command
import com.gunhansancar.mars.game.model.Forward
import com.gunhansancar.mars.game.model.Left
import com.gunhansancar.mars.game.model.Orientation.East
import com.gunhansancar.mars.game.model.Orientation.North
import com.gunhansancar.mars.game.model.Orientation.South
import com.gunhansancar.mars.game.model.Orientation.West
import com.gunhansancar.mars.game.model.Position
import com.gunhansancar.mars.game.model.Right
import org.junit.Assert.assertEquals
import org.junit.Test

class CommandTest {

    @Test
    fun test_command_parse_from_input() {
        Command.from("A")
        listOf(
            Pair("L", Left),
            Pair("R", Right),
            Pair("F", Forward),
            Pair("abc", null),
            Pair("523", null),
            Pair("_xyz", null),
        ).forEach { testParse(it) }
    }

    @Test
    fun test_left_execute() {
        testExecute(Left, Position(1, 5, West), Position(1, 5, South))
        testExecute(Left, Position(1, 5, East), Position(1, 5, North))
        testExecute(Left, Position(1, 5, North), Position(1, 5, West))
        testExecute(Left, Position(1, 5, South), Position(1, 5, East))
    }

    @Test
    fun test_right_execute() {
        testExecute(Right, Position(1, 5, West), Position(1, 5, North))
        testExecute(Right, Position(1, 5, East), Position(1, 5, South))
        testExecute(Right, Position(1, 5, North), Position(1, 5, East))
        testExecute(Right, Position(1, 5, South), Position(1, 5, West))
    }

    @Test
    fun test_forward_execute() {
        testExecute(Forward, Position(1, 5, West), Position(0, 5, West))
        testExecute(Forward, Position(1, 5, East), Position(2, 5, East))
        testExecute(Forward, Position(1, 5, North), Position(1, 6, North))
        testExecute(Forward, Position(1, 5, South), Position(1, 4, South))
    }

    private fun testExecute(command: Command, position: Position, expected: Position) {
        val result = command.execute(position)
        assertEquals(expected, result)
    }

    private fun testParse(input: Pair<String, Any?>) {
        val result = Command.from(input.first)
        assertEquals(input.second, result)
    }
}