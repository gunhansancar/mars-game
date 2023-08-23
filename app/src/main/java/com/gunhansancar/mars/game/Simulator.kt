package com.gunhansancar.mars.game

import com.gunhansancar.mars.game.input.SimulationData

/**
 * Simulates the given simulator data using the initial conditions and robot instructions
 * Returns an output for each of the robot
 */
class Simulator {
    fun simulate(data: SimulationData): String {
        val scents = mutableListOf<Scent>()
        val result = StringBuilder()

        data.instructions.forEach { robot ->
            var position = robot.position
            var lost = false

            for (command in robot.commands) {
                val nextPosition = command.execute(position)

                if (
                    nextPosition.x >= data.x ||
                    nextPosition.x < 0 ||
                    nextPosition.y >= data.y ||
                    nextPosition.y < 0
                ) {
                    if (scents.contains(Scent(position.x, position.y))) continue

                    lost = true
                    scents.add(Scent(position.x, position.y))
                    break
                } else {
                    position = nextPosition
                }
            }

            result.append("${position.x} ${position.y} ${position.orientation.value}${if (lost) " LOST" else ""}")

            if (robot != data.instructions.lastOrNull()) {
                result.append("\n")
            }
        }

        return result.toString()
    }
}

data class Scent(val x: Int, val y: Int)