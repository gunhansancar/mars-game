package com.gunhansancar.mars.game.input

import com.gunhansancar.mars.game.model.Command
import com.gunhansancar.mars.game.model.Position

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
