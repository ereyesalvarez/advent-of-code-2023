package advent


import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {
    private val example = "data/14_ex.txt"
    private val input = "data/14_in.txt"

    private val service = Day14()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(136, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(112048, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent)
            assertEquals(64, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(105606, response)

    }
}
