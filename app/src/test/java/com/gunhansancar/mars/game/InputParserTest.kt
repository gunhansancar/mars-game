package com.gunhansancar.mars.game

import com.gunhansancar.mars.game.input.Command.Forward
import com.gunhansancar.mars.game.input.Command.Left
import com.gunhansancar.mars.game.input.Command.Right
import com.gunhansancar.mars.game.input.InputParser
import com.gunhansancar.mars.game.input.Orientation.East
import com.gunhansancar.mars.game.input.Orientation.North
import com.gunhansancar.mars.game.input.Orientation.West
import com.gunhansancar.mars.game.input.Position
import com.gunhansancar.mars.game.input.RobotInstruction
import com.gunhansancar.mars.game.input.SimulationData
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

        val data = parser.parse(input) ?: return

        assertEquals(
            SimulationData(
                6, 4,
                listOf(
                    RobotInstruction(
                        position = Position(x = 1, y = 1, orientation = East),
                        commands = listOf(
                            Right, Forward, Right, Forward, Right, Forward, Right, Forward
                        )
                    ),
                    RobotInstruction(
                        position = Position(x = 3, y = 2, orientation = North),
                        commands = listOf(
                            Forward, Right, Right, Forward,
                            Left, Left, Forward, Forward, Right, Right, Forward,
                            Left, Left
                        )
                    ),
                    RobotInstruction(
                        position = Position(x = 0, y = 3, orientation = West),
                        commands = listOf(
                            Left,
                            Left,
                            Forward,
                            Forward,
                            Forward,
                            Left,
                            Forward,
                            Left,
                            Forward,
                            Left
                        )
                    ),
                )
            ), data
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

        val data = parser.parse(input)

        assertEquals(null, data?.instructions?.size)
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

        val data = parser.parse(input)

        assertEquals(null, data?.instructions?.size)
    }
}