package advent


import model.Point
import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {
    private val example = "data/11_ex.txt"
    private val input = "data/11_in.txt"

    private val service = Day11()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(374, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(9648398, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        var response = service.execute02(puzzleContent, 2)
        assertEquals(374, response)
        response = service.execute02(puzzleContent, 10)
        assertEquals(1030, response)
        response = service.execute02(puzzleContent, 100)
        assertEquals(8410, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        // 82000210
        val response = service.execute02(puzzleContent, 1000000)
        println(response)
        assertEquals(618800410814, response)
    }

    @Test
    fun distance(){
        val r= service.getDistance(Point(1, 6), Point(5, 11))
        assertEquals(9, r)
    }
}