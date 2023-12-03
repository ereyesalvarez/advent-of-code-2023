package advent


import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test {
    private val example = "data/04_ex.txt"
    private val input = "data/04_in.txt"

    private val service = Day04()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(0, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(0, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent)
        assertEquals(0, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(0, response)
    }
}