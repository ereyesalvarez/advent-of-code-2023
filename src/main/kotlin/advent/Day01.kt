package advent

import utils.getAsListOfInt

class Day01 {
    fun execute01(input: String): Int {
        val list = getAsListOfInt(input).filterNotNull()
        return play01(list)
    }

    private fun play01(list: List<Int>): Int{
        return 0
    }
    fun execute02(input: String): Int {
        val list = getAsListOfInt(input).filterNotNull()
        return play02(list)
    }

    private fun play02(list: List<Int>): Int{
        return 1
    }
}