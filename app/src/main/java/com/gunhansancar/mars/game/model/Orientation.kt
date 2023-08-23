package com.gunhansancar.mars.game.model

/**
 * Orientation of a robot
 */
enum class Orientation(val value: String) {
    North("N"), South("S"), West("W"), East("E");

    /** the robot turns left 90 degrees and remains on the current grid point. */
    fun turnLeft(): Orientation = when (this) {
        North -> West
        South -> East
        West -> South
        East -> North
    }

    /** the robot turns right 90 degrees and remains on the current grid point. */
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