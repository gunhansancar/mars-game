package com.gunhansancar.mars.game

import com.gunhansancar.mars.game.input.SimulationData

class Simulator {
    fun simulate(data: SimulationData): String {
        val scents = mutableListOf<Scent>()
        val result = StringBuilder()

        data.instructions.forEach {
            var position = it.position
            var lost = false

            for (direction in it.directions) {
                val nextPosition = position.findNext(direction)

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

            if (it != data.instructions.lastOrNull()) {
                result.append("\n")
            }
        }

        return result.toString()
    }
}

data class Scent(val x: Int, val y: Int)