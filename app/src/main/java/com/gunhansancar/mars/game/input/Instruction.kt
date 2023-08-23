package com.gunhansancar.mars.game.input

import com.gunhansancar.mars.game.input.Command.Forward
import com.gunhansancar.mars.game.input.Command.Left
import com.gunhansancar.mars.game.input.Command.Right

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
    val position: Position, val commands: List<Command>
) : Instruction

/**
 * Position of a robot specifying coordinates of the robot and an orientation
 */
data class Position(val x: Int, val y: Int, val orientation: Orientation)

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
 * Commands that a robot can execute in a simulation
 *
 * Currently robots can execute [Left], [Right], [Forward] commands
 * i.e. 'RFLF' means a robot goes Right, Forward, Left, and Forward
 */
enum class Command {
    Left, Right, Forward;

    private fun findForward(position: Position): Pair<Int, Int> = when (position.orientation) {
        Orientation.North -> Pair(position.x, position.y + 1)
        Orientation.South -> Pair(position.x, position.y - 1)
        Orientation.West -> Pair(position.x - 1, position.y)
        Orientation.East -> Pair(position.x + 1, position.y)
    }

    /**
     * Finds the next position after executing this command
     */
    fun execute(position: Position): Position = when (this) {
        Left -> Position(position.x, position.y, position.orientation.turnLeft())
        Right -> Position(position.x, position.y, position.orientation.turnRight())
        Forward -> {
            val forward = findForward(position)
            Position(forward.first, forward.second, position.orientation)
        }
    }


    companion object {
        fun from(input: String): Command? = when (input) {
            "L" -> Left
            "R" -> Right
            "F" -> Forward
            else -> null
        }
    }
}