package advent


import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {
    private val example = "data/01_ex.txt"
    private val example2 = "data/01_ex2.txt"
    private val input = "data/01_in.txt"

    private val service = Day01()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(142, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(54940, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example2)
        val response = service.execute02(puzzleContent)
        assertEquals(281, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(54208, response)
    }
}