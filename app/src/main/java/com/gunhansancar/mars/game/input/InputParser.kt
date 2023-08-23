package com.gunhansancar.mars.game.input

import com.gunhansancar.mars.game.Constants

/**
 * Parses the given input for the robots. Splits the instruction into [Instruction]
 * which can be used to simulate a game of Mars
 */
class InputParser {
    private val boardInstructionParser = BoardInstructionParser()
    private val robotInstructionParser = RobotInstructionParser()

    fun parse(input: String): List<Instruction> {
        if (input.length > Constants.InputMaxSize) return emptyList()

        val lines = input.trim().split("\n")

        if (lines.isEmpty()) return emptyList()

        val result = mutableListOf<Instruction>()

        val boardInstruction = boardInstructionParser.parse(lines[0]) ?: return result
        result.add(boardInstruction)

        val robotInstruction = robotInstructionParser.parse(lines.subList(1, lines.size))
        result.addAll(robotInstruction)

        return result
    }
}
