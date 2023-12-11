package advent

import model.Point
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day11 {
    fun execute01(input: String): Long {
        val newMap = findEmpties(input)
        val galaxies = getGalaxies(input.lines())
        var sum = 0L
        galaxies.indices.forEach { i ->
            (i + 1..<galaxies.size).forEach {
                sum += getDistance(galaxies[i], galaxies[it], newMap, 2 - 1)
            }
        }
        return sum
    }

    fun execute02(input: String, value: Int): Long {
        val newMap = findEmpties(input)
        val galaxies = getGalaxies(input.lines())
        var sum = 0L
        galaxies.indices.forEach { i ->
            (i + 1..<galaxies.size).forEach {
                sum += getDistance(galaxies[i], galaxies[it], newMap, value - 1)
            }
        }
        return sum
    }

    private fun getDistance(a: Point, b: Point, empties: Pair<List<Int>, List<Int>>, value: Int = 1): Int {
        val xRange = min(a.x, b.x) + 1..<max(a.x, b.x)
        val yRange = min(a.y, b.y) + 1..<max(a.y, b.y)
        var extra = 0
        xRange.forEach {
            if (empties.first.contains(it)) {
                extra += value
            }
        }
        yRange.forEach {
            if (empties.second.contains(it)) {
                extra += value
            }
        }
        return getDistance(a, b) + extra
    }

    fun getDistance(a: Point, b: Point): Int {
        val x = abs(a.x - b.x)
        val y = abs(a.y - b.y)
        return x + y
    }

    private fun checkH(input: List<String>, x: Int): Boolean {
        return input.all { it[x] == '.' }
    }

    private fun getGalaxies(lines: List<String>): List<Point> {
        val xIndex = lines.first().indices
        val response = mutableListOf<Point>()
        lines.indices.forEach { y ->
            xIndex.forEach { x ->
                if (lines[y][x] == '#') {
                    response.add(Point(x, y))
                }
            }
        }
        return response
    }

    private fun findEmpties(input: String): Pair<List<Int>, List<Int>> {
        val lines = input.lines()
        val xLines = mutableListOf<Int>()
        val yLines = mutableListOf<Int>()
        lines.indices.forEach { y ->
            if (lines[y].all { it == '.' }) {
                yLines.add(y)
            }
        }
        val xIndex = lines[0].indices
        // Map second part
        xIndex.reversed().forEach { x ->
            if (checkH(lines, x)) {
                xLines.add(x)
            }
        }
        return Pair(xLines, yLines)
    }
}