package advent


import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {
    private val example = "data/15_ex.txt"
    private val input = "data/15_in.txt"

    private val service = Day15()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(1320, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(0, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent)
        assertEquals(0, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(0, response)
    }

    @Test
    fun check(){
        var r = service.hash(72)
        assertEquals(200, r)
        r = service.hash('H')
        assertEquals(200, r)
        r = service.hash("HASH")
        assertEquals(52, r)



        r = service.hash("rn=1")
        assertEquals(30, r)
        r = service.hash("cm-")
        assertEquals(253, r)
        r = service.hash("qp=3")
        assertEquals(97, r)

    }
}
