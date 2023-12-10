package advent

import model.Point

class Day10 {
    fun execute01(input: String): Long {
        val puzzle = map(input)
        val s = findTheS(puzzle)
        val ways = getNextS(puzzle, s)
        return iterate(puzzle, ways[0], ways[1], s).toLong()
    }
    fun execute02(input: String, countS: Boolean = true): Long {
        val puzzle = map(input)
        val s = findTheS(puzzle)
        val ways = getNextS(puzzle, s)
        val x = iterate2(puzzle, ways[0], ways[1], s)
        val formatted = format(x, puzzle.size, puzzle)
        val count = countCross(formatted, countS)
        return count
    }
    private fun countCross(input: List<List<Char>>, includeS: Boolean = true): Long {
        var count = 0L
        val pipes = mutableListOf('|', 'L', 'J')
        if (includeS) {
            pipes.add('S')
        }
        input.forEach { l ->
            var c = 0
            l.indices.forEach { i ->
                val it = l[i]
                if (it != '.') {
                    if (pipes.contains(it)) {
                        c++
                        print("#")
                    }else{
                        print("-")
                    }
                } else {
                    if (c.mod(2) != 0) {
                        count++
                        print("0")
                    } else {
                        print(".")
                    }
                }
            }
            println()
        }
        return count
    }

    private fun iterate(puzzle: List<List<Char>>, a: Point, b: Point, s: Point): Int {
        var steps = 1
        var prevA = s
        var prevB = s
        var actualA = a
        var actualB = b
        var find = false
        do {
            steps++
            val newA = getNext(puzzle, actualA, prevA)
            val newB = getNext(puzzle, actualB, prevB)
            if (newA == newB) {
                find = true
            }
            if (newA == actualB) {
                find = true
            }
            prevA = actualA
            prevB = actualB
            actualA = newA
            actualB = newB
        } while (!find)
        return steps

    }

    private fun iterate2(puzzle: List<List<Char>>, a: Point, b: Point, s: Point): Set<Point> {
        val result = mutableSetOf(s, a, b)
        var steps = 1
        var prevA = s
        var prevB = s
        var actualA = a
        var actualB = b
        var find = false
        do {
            steps++
            val newA = getNext(puzzle, actualA, prevA)
            val newB = getNext(puzzle, actualB, prevB)
            result.add(newA)
            result.add(newB)
            if (newA == newB) {
                find = true
            }
            if (newA == actualB) {
                find = true
            }
            prevA = actualA
            prevB = actualB
            actualA = newA
            actualB = newB
        } while (!find)
        return result

    }

    private fun getNextS(puzzle: List<List<Char>>, point: Point): MutableList<Point> {
        val points = mutableListOf<Point>()
        // TOP
        var p: Point = point.moveT()
        var pipe = puzzle[p.y][p.x]
        if (listOf('|', '7', 'F').contains(pipe)) {
            points.add(p)
        }
        // BOT
        p = point.moveB()
        pipe = puzzle[p.y][p.x]
        if (listOf('|', 'J', 'L').contains(pipe)) {
            points.add(p)
        }
        // RIGHT
        p = point.moveR()
        pipe = puzzle[p.y][p.x]
        if (listOf('-', 'J', '7').contains(pipe)) {
            points.add(p)
        }
        // LEFT
        p = point.moveL()
        pipe = puzzle[p.y][p.x]
        if (listOf('-', 'L', 'F').contains(pipe)) {
            points.add(p)
        }
        assert(points.size == 2)
        return points
    }

    private fun getNext(puzzle: List<List<Char>>, point: Point, prev: Point): Point {
        val points = when (val actualPipe = puzzle[point.y][point.x]) {
            '|' -> listOf(point.moveT(), point.moveB())
            '-' -> listOf(point.moveR(), point.moveL())
            'L' -> listOf(point.moveR(), point.moveT())
            'J' -> listOf(point.moveL(), point.moveT())
            '7' -> listOf(point.moveL(), point.moveB())
            'F' -> listOf(point.moveR(), point.moveB())
            else -> {
                println("pipe: $actualPipe")
                throw RuntimeException("Not mapped pipe $actualPipe")
            }
        }
        return points.first { it != prev }
    }


    fun map(input: String): List<List<Char>> {
        return input.lines().map { it.toCharArray().toList() }
    }

    private fun findTheS(puzzle: List<List<Char>>): Point {
        puzzle.indices.forEach { y ->
            puzzle[y].indices.forEach { x ->
                if (puzzle[y][x] == 'S') {
                    return Point(x, y)
                }
            }
        }
        throw RuntimeException("S not found in the puzzle")
    }

    private fun format(points: Set<Point>, size: Int, puzzle: List<List<Char>>): List<List<Char>> {
        val result: MutableList<List<Char>> = mutableListOf()
        (0..<size).forEach { y ->
            val line = mutableListOf<Char>()
            (0..<size).forEach { x ->
                if (points.contains(Point(x, y))) {
                    line.add(puzzle[y][x])
                } else {
                    line.add('.')
                }
            }
            result.add(line)
        }
        return result
    }
}