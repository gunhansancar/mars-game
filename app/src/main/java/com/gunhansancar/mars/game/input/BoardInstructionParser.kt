package com.gunhansancar.mars.game.input

class BoardInstructionParser {
    private val coordinateParser = CoordinateParser()

    fun parse(input: String): BoardInstruction? {
        val items = input.trim().split(" ")

        if (items.size != 2) return null

        val x = coordinateParser.parse(items[0]) ?: return null
        val y = coordinateParser.parse(items[1]) ?: return null

        return BoardInstruction(x, y)
    }
}