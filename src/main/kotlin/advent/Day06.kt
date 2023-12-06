package advent

class Day06 {
    fun execute01(input: String): Long {
        val puzzle = map(input)

        return puzzle.map{ countPossibilities(it) }
            .fold(1) { acc, r -> acc * r }
    }

    fun execute02(input: String): Long {
        val puzzle = map2(input)
        return countPossibilities(puzzle)
    }

    private fun countPossibilities(puzzle: Pair<Long, Long>): Long{
        val time = puzzle.first
        return (0..time).map {
            val t = it * (time - it)
            t
        }.count {
            it > puzzle.second
        }.toLong()
    }

    private fun map(input: String): List<Pair<Long, Long>>{
        val times = input.lines().first().split("Time: ")[1].split(" ").filter { !it.isEmpty() }.map { it.trim() }
            .map { it.toLong() }
        val distances = input.lines()[1].split("Distance: ")[1].split(" ").filter { it.isNotEmpty() }.map { it.trim() }
            .map { it.toLong() }
        val result = mutableListOf<Pair<Long, Long>>()
        times.indices.forEach {
            result.add(Pair(times[it], distances[it]))
        }
        return result
    }

    private fun map2(input: String): Pair<Long, Long>{
        val time = input.lines().first().split("Time: ")[1]
            .replace("\\s".toRegex(), "").toLong()
        val distance = input.lines()[1].split("Distance: ")[1]
            .replace("\\s".toRegex(), "").toLong()
        return Pair(time, distance)
    }
}