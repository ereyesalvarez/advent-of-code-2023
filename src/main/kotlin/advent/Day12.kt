package advent

import utils.findMCM

class Day12 {


    fun String.collapse(): String {
        var x = this
        while (x.contains("..")) {
            x = x.replace("..", ".")
        }
        return x
    }

    fun execute01(input: String): Long {
        var count = 0
        val map = input.lines().map { it.split(" ") }
            .map { l -> Pair(l.first().collapse(), l.last().split(",").map { it.toInt() }) }.asSequence()
        return map.sumOf {
            println(count++)
            calculatePossibilities(it.first, it.second).count { l -> calculateInt(l) == it.second }
                .toLong()
        }
    }

    fun calculatePossibilities(input: String): List<String> {
        var x = listOf(input)
        var count = 0
        while (!x.none { it.contains('?') }) {
            x = x.flatMap {
                listOf(it.replaceFirst("?", ".").collapse(), it.replaceFirst("?", "#"))
            }
            count++
        }
        println(count)
        return x
    }

    fun check(input: String, expected: List<Int>): Boolean {
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

    fun calculateIntWith(input: String): List<Int> {
        var broken = false
        var response = mutableListOf<Int>()
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


    fun calculatePossibilities(input: String, expected: List<Int>): List<String> {
        var count = 0
        var pairs = mutableListOf<Pair<String, Int>>()
        pairs.add(Pair(input, 1))
        while (pairs.map { it.first }.any { it.contains('?') }){
            val z = mutableListOf<Pair<String, Int>>()
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
            pairs = z
        }
        val x = pairs.map { it.first }
        println("x size: ${x.size}  <--- $input")
        return x
    }

    fun mapToList(input: String): MutableList<Pair<Char, Int>> {
        val response = mutableListOf<Pair<Char, Int>>()
        var current = '0'
        var count = 0
        input.forEach {
            if (current == '0') {
                current = it
                count++
            } else if (it == current) {
                count++
            } else {
                response.add(Pair(current, count))
                count = 1
                current = it
            }
        }
        response.add(Pair(current, count))
        return response
    }

    fun calculateInt(input: String): List<Int> {
        var broken = false
        var response = mutableListOf<Int>()
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

    fun execute02(input: String): Long {
        var count = 0
        val map = input.lines().map { it.split(" ") }
            .map { it -> Pair(it.first(), it.last().split(",").map { it.toInt() }) }.asSequence()
        return map.map {
            unfold(it)
        }.sumOf {
            val m = calculatePossibilities(it.first, it.second)
            val r = m.count { l -> calculateInt(l) == it.second }.toLong()
            println("${count++} -> $r")
            r
        }
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
