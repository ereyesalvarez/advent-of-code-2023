package advent


import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {
    private val example = "data/13_ex.txt"
    private val input = "data/13_in.txt"

    private val service = Day13()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(405, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(42974, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent)
        assertEquals(400, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(27587, response)

    }

    @Test
    fun isParallel() {
        var r = service.isParallel(listOf(0, 0, 0, 0))
        assertEquals(true, r)
        r = service.isParallel(listOf(0, 0, 1, 0))
        assertEquals(false, r)
    }

    @Test
    fun getParralelTest(){
        //# # # # # # . . . # .
        val x = service.getParallelCandidates(listOf(1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0))
        assert(x.contains(3))
    }

}
