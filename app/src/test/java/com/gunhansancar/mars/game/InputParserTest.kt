package com.gunhansancar.mars.game

import com.gunhansancar.mars.game.input.InputParser
import com.gunhansancar.mars.game.input.RobotInstruction
import com.gunhansancar.mars.game.input.SimulationData
import com.gunhansancar.mars.game.model.Forward
import com.gunhansancar.mars.game.model.Left
import com.gunhansancar.mars.game.model.Orientation
import com.gunhansancar.mars.game.model.Position
import com.gunhansancar.mars.game.model.Right
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
                        position = Position(x = 1, y = 1, orientation = Orientation.East),
                        commands = listOf(
                            Right, Forward, Right, Forward, Right, Forward, Right, Forward
                        )
                    ),
                    RobotInstruction(
                        position = Position(x = 3, y = 2, orientation = Orientation.North),
                        commands = listOf(
                            Forward, Right, Right, Forward,
                            Left, Left, Forward, Forward, Right, Right, Forward,
                            Left, Left
                        )
                    ),
                    RobotInstruction(
                        position = Position(x = 0, y = 3, orientation = Orientation.West),
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