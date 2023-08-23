package com.gunhansancar.mars.game.input

/**
 * Parses instructions given by the user which contains two sets of data
 * First line contains X, Y, and orientation of the robot
 * Second line contains the commands that a robot can execute
 *
 * i.e.
 * 1 1 E
 * RFRFRFRF
 */
class RobotInstructionParser {
    private val coordinateParser = CoordinateParser()

    fun parse(inputs: List<String>): List<RobotInstruction> {
        val robotLines = inputs.filter { it != "\n" && it.isNotBlank() }

        val result = mutableListOf<RobotInstruction>()
        var i = 0
        while (i + 1 < robotLines.size) {
            val position = parsePosition(robotLines[i])
            val command = parseCommands(robotLines[i + 1])

            if (position != null) {
                result.add(RobotInstruction(position, command))
            }

            i += 2
        }

        return result
    }

    private fun parsePosition(input: String): Position? {
        val items = input.trim().split(" ")

        if (items.size != 3) {
            return null
        }

        val x = coordinateParser.parse(items[0]) ?: return null
        val y = coordinateParser.parse(items[1]) ?: return null
        val orientation = Orientation.from(items[2]) ?: return null

        return Position(x, y, orientation)
    }

    private fun parseCommands(input: String): List<Command> =
        input.toCharArray().map { Command.from(it.toString()) ?: return emptyList() }
}