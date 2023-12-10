package advent

import model.Point
import utils.toLongList

class Day10 {
    fun execute01(input: String): Long {
        /*
        | is a vertical pipe connecting north and south.
        - is a horizontal pipe connecting east and west.
        L is a 90-degree bend connecting north and east.
        J is a 90-degree bend connecting north and west.
        7 is a 90-degree bend connecting south and west.
        F is a 90-degree bend connecting south and east.
        . is ground; there is no pipe in this tile.
        S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.
         */
        val puzzle =  map(input)
        val s = findTheS(puzzle)
        println(s)
        var ways = getNextS(puzzle, s)
        println(ways)
        return iterate(puzzle, ways[0], ways[1], s).toLong()
    }

    fun iterate(puzzle: List<List<Char>>, a: Point, b: Point, s: Point): Int {
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
            println("S $steps   A: $newA B: $newB")
            if (newA == newB){
                find = true
            }
            if (newA == actualB){
                find = true
            }
            prevA = actualA
            prevB = actualB
            actualA = newA
            actualB = newB
        } while (!find)
        return steps

    }

    fun getNextS(puzzle: List<List<Char>>, point: Point): MutableList<Point> {
        val points = mutableListOf<Point>()
        var p: Point
        // TOP
        p = point.moveT()
        var pipe = puzzle[p.y][p.x]
        if (listOf('|', '7', 'F').contains(pipe)){
            points.add(p)
        }
        // BOT
        p = point.moveB()
        pipe = puzzle[p.y][p.x]
        if (listOf('|', 'J', 'L').contains(pipe)){
            points.add(p)
        }
        // RIGHT
        p = point.moveR()
        pipe = puzzle[p.y][p.x]
        if (listOf('-', 'J', '7').contains(pipe)){
            points.add(p)
        }
        // LEFT
        p = point.moveL()
        pipe = puzzle[p.y][p.x]
        if (listOf('-', 'L', 'F').contains(pipe)){
            points.add(p)
        }
        assert(points.size == 2)
        return points
    }
    fun getNext(puzzle: List<List<Char>>, point: Point, prev: Point): Point{
        val points = when (val actualPipe = puzzle[point.y][point.x]){
            '|' -> listOf(point.moveT(), point.moveB())
            '-' -> listOf(point.moveR(), point.moveL())
            'L' -> listOf(point.moveR(), point.moveT())
            'J' -> listOf(point.moveL(), point.moveT())
            '7' -> listOf(point.moveL(), point.moveB())
            'F' -> listOf(point.moveR(), point.moveB())
            else -> {
                println("pipe: $actualPipe")
                TODO()
            }
        }
        return points.first { it != prev }
    }

    fun execute02(input: String): Long {
        var puzzle =  map(input)
        return  0
    }
    fun map(input: String): List<List<Char>> {
        return input.lines().map { it.toCharArray().toList() }
    }

    fun findTheS(puzzle: List<List<Char>>): Point{
        puzzle.indices.forEach {y ->
            puzzle[y].indices.forEach{x->
                if (puzzle[y][x] == 'S'){
                    println("S")
                    return Point(x, y)
                }
            }
        }
        TODO()
    }
}