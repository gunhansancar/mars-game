package com.gunhansancar.mars.game

import com.gunhansancar.mars.game.input.BoardInstruction
import com.gunhansancar.mars.game.input.Direction.Forward
import com.gunhansancar.mars.game.input.Direction.Left
import com.gunhansancar.mars.game.input.Direction.Right
import com.gunhansancar.mars.game.input.InputParser
import com.gunhansancar.mars.game.input.Orientation.East
import com.gunhansancar.mars.game.input.Orientation.North
import com.gunhansancar.mars.game.input.Orientation.West
import com.gunhansancar.mars.game.input.Position
import com.gunhansancar.mars.game.input.RobotInstruction
import org.junit.Assert.assertEquals
import org.junit.Test

class InputParserTest {
    private val parser = InputParser()

    @Test
    fun test_parse_success() {
        val input = """
            5 3
            1 1 E 
            RFRFRFRF
            
            3 2 N 
            FRRFLLFFRRFLL
            
            0 3 W 
            LLFFFLFLFL
        """.trimIndent()

        val instructions = parser.parse(input)

        assertEquals(4, instructions.size)
        assertEquals(BoardInstruction(x = 5, y = 3), instructions[0])
        assertEquals(
            RobotInstruction(
                position = Position(x = 1, y = 1, orientation = East),
                directions = listOf(
                    Right, Forward, Right, Forward, Right, Forward, Right, Forward
                )
            ),
            instructions[1]
        )
        assertEquals(
            RobotInstruction(
                position = Position(x = 3, y = 2, orientation = North),
                directions = listOf(
                    Forward, Right, Right, Forward,
                    Left, Left, Forward, Forward, Right, Right, Forward,
                    Left, Left
                )
            ), instructions[2]
        )
        assertEquals(
            RobotInstruction(
                position = Position(x = 1, y = 1, orientation = East),
                directions = listOf(
                    Right, Forward, Right, Forward, Right, Forward, Right, Forward
                )
            ),
            instructions[1]
        )
        assertEquals(
            RobotInstruction(
                position = Position(x = 0, y = 3, orientation = West),
                directions = listOf(
                    Left, Left, Forward, Forward, Forward, Left, Forward, Left, Forward, Left
                )
            ), instructions[3]
        )
    }

    @Test
    fun test_parse_fail() {
        val input = """
            53
            1 1 E 
            RFRFRFRF
            
            3 2 N 
            FRRFLLFFRRFLL
            
            0 3 W 
            LLFFFLFLFL
        """.trimIndent()

        val instructions = parser.parse(input)

        assertEquals(0, instructions.size)
    }

    @Test
    fun test_parse_max_size() {
        val input = """
            5 3
            1 1 E 
            RFRFRFRF
            
            3 2 N 
            FRRFLLFFRRFLL
            
            0 3 W 
            LLFFFLFLFL
            
            5 3
            1 1 E 
            RFRFRFRF
            
            3 2 N 
            FRRFLLFFRRFLL
            
            0 3 W 
            LLFFFLFLFL
            
            5 3
            1 1 E 
            RFRFRFRF
            
            3 2 N 
            FRRFLLFFRRFLL
            
            0 3 W 
            LLFFFLFLFL
            
            5 3
            1 1 E 
            RFRFRFRF
            
            3 2 N 
            FRRFLLFFRRFLL
            
            0 3 W 
            LLFFFLFLFL
            
            5 3
            1 1 E 
            RFRFRFRF
            
            3 2 N 
            FRRFLLFFRRFLL
            
            0 3 W 
            LLFFFLFLFL
            
        """.trimIndent()

        val instructions = parser.parse(input)

        assertEquals(0, instructions.size)
    }
}