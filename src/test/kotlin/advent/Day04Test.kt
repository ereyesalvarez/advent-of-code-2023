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
        assertEquals(13, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(23441, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent)
        assertEquals(30, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(5923918, response)
    }

    @Test
    fun map() {
        val l = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
        val result = service.map(listOf(l)).first()
        assertEquals(result.id, 1)
        assertEquals(result.win, listOf(41, 48, 83, 86, 17))
        assertEquals(result.numbers, listOf(83, 86, 6, 31, 17, 9, 48, 53))
    }
}