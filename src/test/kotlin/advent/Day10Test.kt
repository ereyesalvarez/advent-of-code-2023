package advent


import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    private val example = "data/10_ex.txt"
    private val example2 = "data/10_ex2.txt"
    private val example3 = "data/10_ex3.txt"
    private val input = "data/10_in.txt"

    private val service = Day10()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(4, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(6768, response)
    }
    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent, false)
        assertEquals(1, response)
    }
    @Test
    fun example2B() {
        val puzzleContent = getFileAsText(example2)
        val response = service.execute02(puzzleContent)
        assertEquals(4, response)
    }

    @Test
    fun example3() {
        val puzzleContent = getFileAsText(example3)
        val response = service.execute02(puzzleContent, false)
        assertEquals(4, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent, false)
        println(response)
        assertEquals(351, response)
    }


}