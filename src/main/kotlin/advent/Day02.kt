package advent

class Day02 {
    data class CubeGame(val blue: Int, val green: Int, val red: Int)

    // only 12 red cubes, 13 green cubes, and 14 blue cubes
    private val redCubes: Int = 12
    private val greenCubes: Int = 13
    private val blueCubes: Int = 14

    private fun map(line: String): Pair<Int, List<CubeGame>> {
        val a = line.split("Game ")
        val b = a.last().split(": ")
        val id = b.first().toInt()
        val c = b.last().split("; ")
        val games = c.map {
            val x = it.split(", ")
            var blue = 0
            var green = 0
            var red = 0
            x.forEach { n ->
                val r = n.split(" ")
                when (r.last()) {
                    "blue" -> blue = r.first().toInt()
                    "green" -> green = r.first().toInt()
                    "red" -> red = r.first().toInt()
                }
            }
            CubeGame(blue, green, red)
        }
        return Pair(id, games)
    }

    fun execute01(input: String): Int {
        val mapped = input.lines().map { map(it) }
        val filter = mapped.filter {
            var valid = true
            it.second.forEach { c ->
                if (valid) {
                    if (c.blue > blueCubes || c.green > greenCubes || c.red > redCubes) {
                        valid = false
                    }
                }
            }
            valid
        }
        return filter.sumOf { it.first }
    }

    fun execute02(input: String): Int {
        val mapped = input.lines().map { map(it) }
        val calc = mapped.map { pair ->
            var minRed = 0
            var minBlue = 0
            var minGreen = 0
            pair.second.forEach {
                if (minRed < it.red){
                    minRed = it.red
                }
                if (minBlue < it.blue){
                    minBlue = it.blue
                }
                if (minGreen < it.green){
                    minGreen = it.green
                }
            }
            minRed * minBlue * minGreen
        }
        return calc.sum()
    }
}

