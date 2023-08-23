package com.gunhansancar.mars.game.input

class RobotInstructionParser {
    private val coordinateParser = CoordinateParser()

    fun parse(inputs: List<String>): List<RobotInstruction> {
        val result = mutableListOf<RobotInstruction>()
        if (inputs.isEmpty()) {
            return result
        }

        var i = 0
        while (i < inputs.size) {
            val position = parsePosition(inputs[i])
            val direction = parseDirections(inputs[i + 1])

            if(position != null) {
                result.add(RobotInstruction(position, direction))
            }

            i += 3
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

    private fun parseDirections(input: String): List<Direction> =
        input.toCharArray().map { Direction.from(it.toString()) ?: return emptyList() }
}