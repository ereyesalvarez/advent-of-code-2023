package advent

import utils.mapStringToDigit

class Day01 {
    fun execute01(input: String): Int {
        val mapped = input.lines().map { s -> s.map { it.digitToIntOrNull(10) }.filterNotNull() }
        return play01(mapped)
    }

    fun execute02(input: String): Int {
        val mapped = input.lines().map { map02(it) }
        return play01(mapped)
    }

    private fun play01(list: List<List<Int>>): Int {
        return list
            .map { "${it.first()}${it.last()}" }
            .sumOf { it.toInt() }
    }

    private fun map02(input: String): List<Int> {
        val pattern = Regex("(?=(one|two|three|four|five|six|seven|eight|nine|\\d))")
        val matches = pattern.findAll(input)
        val groups = matches.map { g ->
            g.groups.filterNotNull()
                .map { it.value }
                .first { it.isNotEmpty() }
        }.toList()
        return groups.map { g -> mapStringToDigit(g) }
    }
}