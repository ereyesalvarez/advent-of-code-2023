package advent

import utils.findMCM

class Day08 {


    fun execute01(input: String): Long {
        val instructions = input.lines().first().toCharArray()
        val puzzle = map(input)
        var count = 0
        var actual = "AAA"
        do {
            actual = iterate(actual, instructions[count % instructions.size], puzzle)
            count++
        } while (actual != "ZZZ")
        return count.toLong()
    }

    private fun iterate(actual: String, move: Char, map: Map<String, Pair<String, String>>): String {
        val a = map[actual]!!
        if (move == 'L') {
            return a.first
        }
        if (move == 'R') {
            return a.second
        }
        TODO()
    }

    fun execute02(input: String): Long {
        val instructions = input.lines().first().toCharArray()
        val puzzle = map(input)
        val actual = puzzle.keys.filter { it.endsWith("A") }.toSet()
        val solutionsUtilZ = actual.map { calculatePossibilities(it, puzzle, instructions) }
        return findMCM(solutionsUtilZ.map { it.first().toLong() }.toSet().toList())
    }


    private fun calculatePossibilities(input: String, map: Map<String, Pair<String, String>>, instructions: CharArray): List<Int> {
        var actual = input
        var count = 0
        val positions = mutableListOf<Int>()
        val gets = mutableListOf<Pair<String, Int>>()
        var cont = true
        do {
            val nA = actual
            val pos = (count % instructions.size)
            actual = iterate(actual, instructions[pos], map)
            count++
            if (actual.endsWith("Z")) {
                if (gets.contains(Pair(nA, pos))){
                    cont = false
                } else {
                    gets.add(Pair(nA, pos))
                    positions.add(count)
                }
            }
        } while (cont)
        return positions
    }

    fun map(input: String): Map<String, Pair<String, String>> {
        return input.lines().drop(2).associate {
            val split = it.split(" = ")
            val par = split.last().replace("(", "").replace(")", "").split(", ")
            Pair(split.first(), Pair(par.first(), par.last()))
        }
    }

}
