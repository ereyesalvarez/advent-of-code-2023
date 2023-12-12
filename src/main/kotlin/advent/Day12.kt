package advent

class Day12 {


    private fun String.collapse(): String {
        var x = this
        while (x.contains("..")) {
            x = x.replace("..", ".")
        }
        return x
    }

    fun execute01(input: String): Long {
        val map =
            input.lines().map { it.split(" ") }.map { l -> Pair(l.first().collapse(), l.last().split(",").map { it.toInt() }) }.asSequence()
        return map.sumOf {
            calculatePossibilities(it.first, it.second)
        }
    }

    fun execute02(input: String): Long {
        val map = input.lines().map { it.split(" ") }.map { it -> Pair(it.first(), it.last().split(",").map { it.toInt() }) }.asSequence()
        return map.map {
            unfold(it)
        }.sumOf {
            calculatePossibilities(it.first, it.second)
        }
    }

    private fun check(input: String, expected: List<Int>): Boolean {
        val n = calculateIntWith(input)
        if (n.size > expected.size) {
            return false
        }
        n.indices.forEach {
            if (n[it] != expected[it]) {
                return false
            }
        }
        return true
    }

    private fun calculateIntWith(input: String): List<Int> {
        var broken = false
        val response = mutableListOf<Int>()
        var count = 0
        input.forEach {
            if (it == '?') {
                return response
            }
            if (broken) {
                if (it == '#') {
                    count++
                } else {
                    response.add(count)
                    count = 0
                    broken = false
                }
            } else {
                if (it == '#') {
                    broken = true
                    count++
                }
            }
        }
        if (input.last() == '#') {
            response.add(count)
        }
        return response
    }


    private fun calculatePossibilities(input: String, expected: List<Int>): Long {
        var pairs = mutableListOf<Pair<String, Long>>()
        pairs.add(Pair(input, 1))
        while (pairs.map { it.first }.any { it.contains('?') }) {
            val z = mutableListOf<Pair<String, Long>>()
            pairs.forEach {
                val a = it.first.replaceFirst("?", ".").collapse()
                val b = it.first.replaceFirst("?", "#").collapse()
                if (check(a, expected)) {
                    z.add(Pair(a, it.second))
                }
                if (check(b, expected)) {
                    z.add(Pair(b, it.second))
                }
            }
            //reduce
            val o = z.groupBy { it.first }
            pairs = o.map { m -> Pair(m.key, m.value.sumOf { it.second }) }.toMutableList()
            if(pairs.filter { it.second < 1 }.any()){
                println("ERROR $input")
            }
            // pairs = z
        }
        val response = pairs.filter { l -> calculateInt(l.first) == expected }.sumOf { l -> l.second.toLong() }
        println(response)
        return response.toLong()
    }

    private fun calculateInt(input: String): List<Int> {
        var broken = false
        val response = mutableListOf<Int>()
        var count = 0
        input.forEach {
            if (broken) {
                if (it == '#') {
                    count++
                } else {
                    response.add(count)
                    count = 0
                    broken = false
                }
            } else {
                if (it == '#') {
                    broken = true
                    count++
                }
            }
        }
        if (input.last() == '#') {
            response.add(count)
        }
        return response
    }

    private fun unfold(input: Pair<String, List<Int>>): Pair<String, List<Int>> {
        val m = mutableListOf<Int>()
        val n = input.first
        m.addAll(input.second)
        m.addAll(input.second)
        m.addAll(input.second)
        m.addAll(input.second)
        m.addAll(input.second)
        return Pair("$n?$n?$n?$n?$n", m)
    }
}
