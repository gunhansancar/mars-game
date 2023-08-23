package com.gunhansancar.mars.game.model

/**
 * Commands that a robot can execute in a simulation
 *
 * Currently robots can execute [Left], [Right], [Forward] commands
 * i.e. 'RFLF' means a robot goes Right, Forward, Left, and Forward
 */
sealed class Command {
    /**
     * Finds the next position after executing this command
     */
    abstract fun execute(position: Position): Position

    companion object {
        /**
         * Creates a [Command] object from an input string
         *
         * If the command is not found returns `null`
         */
        fun from(input: String): Command? = when (input) {
            "L" -> Left
            "R" -> Right
            "F" -> Forward
            else -> null
        }
    }
}

/** the robot turns left 90 degrees and remains on the current grid point. */
object Left : Command() {
    override fun execute(position: Position): Position =
        Position(position.x, position.y, position.orientation.turnLeft())
}

/** the robot turns right 90 degrees and remains on the current grid point. */
object Right : Command() {
    override fun execute(position: Position): Position =
        Position(position.x, position.y, position.orientation.turnRight())
}

/**
 * the robot moves forward one grid point in the direction of the current
 * orientation and maintains the same orientation.
 */
object Forward : Command() {
    private fun findForward(position: Position): Pair<Int, Int> = when (position.orientation) {
        Orientation.North -> Pair(position.x, position.y + 1)
        Orientation.South -> Pair(position.x, position.y - 1)
        Orientation.West -> Pair(position.x - 1, position.y)
        Orientation.East -> Pair(position.x + 1, position.y)
    }

    override fun execute(position: Position): Position {
        val forward = findForward(position)
        return Position(forward.first, forward.second, position.orientation)
    }
}