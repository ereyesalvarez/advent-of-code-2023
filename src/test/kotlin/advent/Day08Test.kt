package advent


import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {
    private val example = "data/08_ex.txt"
    private val example2 = "data/02_ex2.txt"
    private val example3 = "data/08_ex3.txt"
    private val input = "data/08_in.txt"

    private val service = Day08()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(2, response)
    }

    @Test
    fun example1B() {
        val puzzleContent = getFileAsText(example2)
        val response = service.execute01(puzzleContent)
        assertEquals(6, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(12643, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example3)
        val response = service.execute02(puzzleContent)
        assertEquals(6, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(13133452426987, response)
    }


}