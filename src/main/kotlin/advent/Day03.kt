package advent

import java.awt.Point

class Day03 {
    data class NumberFind(val y: Int, val xStart: Int, val xEnd: Int, val value: Int)

    fun execute01(input: String): Int {
        var lines = input.lines()
        var points = getDigitPosition(lines)
        val numbers = getNumbers(lines)
        var sum = 0
        val sum2 = numbers.filter { isNear(it.y, it.xStart, it.xEnd, points) }.sumOf { it.value }
        numbers.forEach {
            if (isNear(it.y, it.xStart, it.xEnd, points)) {
                sum += it.value
                println("Sum ${it.value} => $sum")
            } else {
                println("${it.value} is not near")
            }
        }
        if (numbers.any { it.value > 999 }) {
            println("fuck")
        }
        println(sum2)
        return sum
    }

    fun getNumbers(lines: List<String>): MutableList<NumberFind> {
        val numbers = mutableListOf<NumberFind>()
        var y = 0
        var xStart = 0
        var charArray = mutableListOf<Char>()
        var numberO = false
        lines.forEach { line ->
            if (numberO) {
                val string = String(charArray.toCharArray())
                numbers.add(NumberFind(y - 1, xStart, xStart + string.length - 1, string.toInt()))
                numberO = false
                charArray = mutableListOf()
            }
            var x = 0
            line.forEach {
                if (it.isDigit()) {
                    if (!numberO) {
                        xStart = x
                        numberO = true
                        charArray.add(it)
                    } else {
                        charArray.add(it)
                    }
                } else {
                    if (numberO) {
                        val string = String(charArray.toCharArray())
                        numbers.add(NumberFind(y, xStart, xStart + string.length - 1, string.toInt()))
                        charArray = mutableListOf()
                    }
                    numberO = false
                }
                x++
            }

            y++
        }
        return numbers
    }

    fun execute02(input: String): Int {
        val lines = input.lines()
        val points = getDigitPosition(lines)
        val numbers = getNumbers(lines)

        val gears = mutableMapOf<Point, MutableList<Int>>()
        numbers.forEach {
            val pivots = getPivots(it.y, it.xStart, it.xEnd, points)
            pivots.forEach { p ->
                val list = gears.getOrDefault(p, mutableListOf())
                list.add(it.value)
                gears[p] = list
            }
        }
        var result = 0
        gears.forEach { t, u ->
            if (u.size > 1) {
                result += u.reduce { acc, i -> acc * i }
            }
        }
        return result
    }


    private fun getDigitPosition(input: List<String>): List<Point> {
        val points: MutableList<Point> = mutableListOf()
        var y = 0
        input.forEach {
            var x = 0
            it.forEach { c ->
                if (equalToSymbol(c)) {
                    points.add(Point(x, y))
                }
                x++
            }
            // * % @ # + $ = - / &
            y++
        }
        return points
    }

    // 832
    private fun equalToSymbol(char: Char): Boolean {
        val listOfChars = listOf('*', '%', '@', '#', '+', '$', '=', '-', '/', '&')
        return listOfChars.contains(char)
    }

    private fun getPivots(y: Int, xStart: Int, xEnd: Int, points: List<Point>): List<Point> {
        var pivots = mutableListOf<Point>()


        if (points.contains(Point(xStart - 1, y))) {
            pivots.add(Point(xStart - 1, y))
        }
        if (points.contains(Point(xEnd + 1, y))) {
            pivots.add(Point(xEnd + 1, y))
        }

        val range = (xStart - 1..xEnd + 1)
        range.forEach {
            if (points.contains(Point(it, y - 1))) {
                pivots.add(Point(it, y - 1))
            }
            if (points.contains(Point(it, y + 1))) {
                pivots.add(Point(it, y + 1))
            }
        }
        return pivots
    }


    private fun isNear(y: Int, xStart: Int, xEnd: Int, points: List<Point>): Boolean {
        var valid = false

        if (points.contains(Point(xStart - 1, y))) {
            valid = true
        }
        if (points.contains(Point(xEnd + 1, y))) {
            valid = true
        }

        val range = (xStart - 1..xEnd + 1)
        range.forEach {
            if (points.contains(Point(it, y - 1))) {
                valid = true
            }
            if (points.contains(Point(it, y + 1))) {
                valid = true
            }
        }
        return valid
    }
}
