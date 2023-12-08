package advent


import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Test {
    private val example = "data/09_ex.txt"
    private val input = "data/09_in.txt"

    private val service = Day09()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(114, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(1868368343, response)
    }
    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent)
        assertEquals(2, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(1022, response)
    }


}