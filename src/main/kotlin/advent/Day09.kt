package advent

import utils.toLongList

class Day09 {
    fun execute01(input: String): Long {
        return input.lines().map { it.toLongList() }
            .map { resolveLine(it) }
            .sumOf { calculateNext(it) }
            .toLong()
    }

    fun execute02(input: String): Long {
        return input.lines().map { it.toLongList() }
            .map { resolveLine(it) }
            .sumOf { calculatePrev(it) }
            .toLong()
    }

    private fun calculateNext(input: List<List<Int>>): Int{
        return input
            .reversed()
            .drop(1)
            .fold(0){acc, ints -> acc + ints.last() }
    }
    private fun calculatePrev(input: List<List<Int>>): Int{
            return input
                .reversed()
                .drop(1)
                .fold(0){acc, ints -> ints.first() - acc }
    }

    private fun resolveLine(input: List<Int>): List<List<Int>> {
        var actual = input
        val solution = mutableListOf(actual.toMutableList())
        var change: MutableList<Int>
        do {
            change = mutableListOf()
            (0..<actual.size - 1).forEach {
                change.add(actual[it + 1] - actual[it])
            }
            solution.add(change)
            actual = change
        } while (!change.all { it == 0 })
        return solution
    }
}