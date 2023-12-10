package advent

class Day11 {
    fun execute01(input: String): Long {
        val puzzle = map(input)
        return puzzle.size.toLong()
    }

    fun execute02(input: String): Long {
        val puzzle = map(input)
        return puzzle.size.toLong()
    }

    fun map(input: String): List<String> {
        return input.lines().filter { it.isNotEmpty() }
    }
}