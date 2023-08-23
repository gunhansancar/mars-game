package com.gunhansancar.mars.game.input

interface Instruction {
}

/**
 * Board instruction represents the size of the board
 * @param x represents the x length
 * @param y represents the y length
 */
data class BoardInstruction(
    val x: Int,
    val y: Int
) : Instruction

/**
 * Robot instruction represents how a robot goes to a location
 */
data class RobotInstruction(
    val position: Position,
    val directions: List<Direction>
) : Instruction

/**
 * Position of a robot
 */
data class Position(
    val x: Int,
    val y: Int,
    val orientation: Orientation
)

/**
 * Orientation of a robot
 */
enum class Orientation {
    North, South, West, East;

    companion object {
        fun from(input: String): Orientation? = when (input) {
            "N" -> North
            "S" -> South
            "W" -> West
            "E" -> East
            else -> null
        }
    }
}

/**
 * Direction of a robot
 * i.e. 'RFLF' means a robot goes Right, Forward, Left, and Forward
 */
enum class Direction {
    Left, Right, Forward;

    companion object {
        fun from(input: String): Direction? = when (input) {
            "L" -> Left
            "R" -> Right
            "F" -> Forward
            else -> null
        }
    }
}