package com.gunhansancar.mars.game.input

import com.gunhansancar.mars.game.Constants

/**
 * Parses the given input for the robots. Splits the instruction into [Instruction]
 * which can be used to simulate a game of Mars
 */
class InputParser {
    private val boardInstructionParser = BoardInstructionParser()
    private val robotInstructionParser = RobotInstructionParser()

    fun parse(input: String): SimulationData? {
        if (input.length > Constants.InputMaxSize) return null

        val lines = input.trim().split("\n")

        if (lines.isEmpty()) return null

        val boardInstruction = boardInstructionParser.parse(lines[0]) ?: return null
        val robotInstruction = robotInstructionParser.parse(lines.subList(1, lines.size))

        return SimulationData(boardInstruction.x + 1, boardInstruction.y + 1, robotInstruction)
    }
}

data class SimulationData(val x: Int, val y: Int, val instructions: List<RobotInstruction>)
