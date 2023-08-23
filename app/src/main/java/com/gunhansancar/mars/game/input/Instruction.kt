package com.gunhansancar.mars.game.input

import com.gunhansancar.mars.game.input.Direction.Forward
import com.gunhansancar.mars.game.input.Direction.Left
import com.gunhansancar.mars.game.input.Direction.Right
import com.gunhansancar.mars.game.input.Orientation.East
import com.gunhansancar.mars.game.input.Orientation.North
import com.gunhansancar.mars.game.input.Orientation.South
import com.gunhansancar.mars.game.input.Orientation.West

interface Instruction

/**
 * Board instruction represents the size of the board
 * @param x represents the x length
 * @param y represents the y length
 */
data class BoardInstruction(
    val x: Int, val y: Int
) : Instruction

/**
 * Robot instruction represents how a robot goes to a location
 */
data class RobotInstruction(
    val position: Position, val directions: List<Direction>
) : Instruction

/**
 * Position of a robot
 */
data class Position(
    val x: Int, val y: Int, val orientation: Orientation
) {
    fun findNext(direction: Direction): Position = when (direction) {
        Left -> Position(x, y, orientation.turnLeft())
        Right -> Position(x, y, orientation.turnRight())
        Forward -> {
            val forward = findForward()
            Position(forward.first, forward.second, orientation)
        }
    }

    private fun findForward(): Pair<Int, Int> = when (orientation) {
        North -> Pair(x, y + 1)
        South -> Pair(x, y - 1)
        West -> Pair(x - 1, y)
        East -> Pair(x + 1, y)
    }
}

/**
 * Orientation of a robot
 */
enum class Orientation(val value: String) {
    North("N"), South("S"), West("W"), East("E");

    fun turnLeft(): Orientation = when (this) {
        North -> West
        South -> East
        West -> South
        East -> North
    }

    fun turnRight(): Orientation = when (this) {
        North -> East
        South -> West
        West -> North
        East -> South
    }

    companion object {
        fun from(input: String): Orientation? = when (input) {
            North.value -> North
            South.value -> South
            West.value -> West
            East.value -> East
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