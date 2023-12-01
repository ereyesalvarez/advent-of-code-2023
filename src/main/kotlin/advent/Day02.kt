package advent

import utils.getAsListOfInt
import utils.mapStringToDigit

class Day02 {
    fun execute01(input: String): Int {
        val mapped = input.lines().map { getAsListOfInt(it) }
        return play01(mapped)
    }
    fun execute02(input: String): Int {
        val mapped = input.lines().map { getAsListOfInt(it) }
        return play01(mapped)
    }
    private fun play01(list: List<List<Int>>): Int {
        return list.size
    }
}