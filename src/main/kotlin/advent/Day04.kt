package advent

import kotlin.math.pow

class Day04 {
    fun execute01(input: String): Int {
        val lottery = map(input.lines())
        return lottery.sumOf { calculateWinPoints(it) }
    }

    private fun calculateWinPoints(lottery: Lottery): Int {
        val matches = calculateWin(lottery)
        if (matches > 0) {
            return 2f.pow(matches - 1).toInt()
        }
        return 0
    }

    private fun calculateWin(lottery: Lottery): Int {
        var matches = 0
        lottery.numbers.forEach {
            if (lottery.win.contains(it)) {
                matches++
            }
        }
        return matches
    }

    fun execute02(input: String): Int {
        val lottery = map(input.lines())
        val ticketNumber = lottery.size
        val ticketCount = lottery.associate { Pair(it.id, 1) }.toMutableMap()
        val wins = lottery.map { Pair(it.id, calculateWin(it)) }

        wins.forEach {
            val id = it.first
            val thisWins = it.second
            if (thisWins > 0) {
                val range = (id + 1..id + thisWins)
                val actualN = ticketCount[id]!!
                range.forEach {n ->
                    if (n <= ticketNumber){
                        val value = ticketCount[n]!!
                        val final = value + 1 * actualN
                        ticketCount[n] = final
                    }
                }
            }
        }
        return  ticketCount.map { it.value }.sum()
    }

    fun map(lines: List<String>): List<Lottery> {
        val result = mutableListOf<Lottery>()
        for (line in lines) {
            val a = line.split(": ")
            val id = a.first().split("Card ").last().trim().toInt()
            val numbers = a.last().split(" | ")
            val win = numbers.first().split(" ").filter { it.isNotBlank() }.map { it.trim() }
                .map { it.toInt() }
            val mine = numbers.last().split(" ").filter { it.isNotBlank() }.map { it.trim() }
                .map { it.toInt() }
            result.add(Lottery(id, win, mine))
        }
        return result
    }

    data class Lottery(val id: Int, val win: List<Int>, val numbers: List<Int>)
}