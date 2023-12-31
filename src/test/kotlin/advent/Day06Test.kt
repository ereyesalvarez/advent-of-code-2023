package advent


import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {
    private val example = "data/06_ex.txt"
    private val input = "data/06_in.txt"

    private val service = Day06()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(288, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(1660968, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent)
        assertEquals(71503, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(26499773, response)
    }

}