package com.gunhansancar.mars.game

import com.gunhansancar.mars.game.input.InputParser
import org.junit.Assert.assertEquals
import org.junit.Test

internal class SimulatorTest {
    private val inputParser = InputParser()
    private val simulator = Simulator()

    @Test
    fun simulate() {
        val input = """
            5 3
            1 1 E 
            RFRFRFRF
            
            3 2 N 
            FRRFLLFFRRFLL
            
            0 3 W 
            LLFFFLFLFL
            
        """.trimIndent()

        val output = """
            1 1 E
            3 3 N LOST
            2 3 S
        """.trimIndent()

        val data = inputParser.parse(input)

        assertEquals(true, data != null)

        data?.let {
            val result = simulator.simulate(it)

            assertEquals(output, result)
        }
    }
}