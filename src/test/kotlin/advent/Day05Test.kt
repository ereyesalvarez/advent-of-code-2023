package advent


import org.junit.jupiter.api.Disabled
import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertContains
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
    @Disabled
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent)
        assertEquals(46, response)
    }

    @Test
    @Disabled
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(26714516, response)
    }

    @Test
    fun example1b() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01B(puzzleContent)
        assertEquals(35, response)
    }

    @Test
    fun example2b() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02B(puzzleContent)
        assertEquals(46, response)
    }

    @Test
    fun exercise2b() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02B(puzzleContent)
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

    @Test
    fun calculateIntersection(){
        val a = 79L..79
        val b = 59L..97
        val result = service.calculateIntersection(a, b)
        val expect = listOf(Pair(79..79L, true))
        assertEquals(expect, result)
    }
    @Test
    fun calculateIntersection2(){
        val a = 1L..10
        val b = 11L..100
        val result = service.calculateIntersection(a, b)
        val expect = listOf(Pair(1..10L, false))
        assertEquals(expect, result)
    }
}