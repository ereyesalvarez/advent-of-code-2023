package advent


import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {
    private val example = "data/05_ex.txt"
    private val input = "data/05_in.txt"

    private val service = Day05()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(35L, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(84470622, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent)
        assertEquals(46, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(26714516, response)
    }

    @Test
    fun calculateNext(){
        val data = listOf(
            Pair(2L..3L, 10L),
            Pair(5L..5L, 100L)
        )
        val input = listOf(1L..10)
        val result = service.calculateNext(input, data).sortedBy { it.first }
        val expected = listOf(1L..1, 4L..4, 6L..10, 12L..13, 105L..105)
        assertEquals(expected, result)
    }
}