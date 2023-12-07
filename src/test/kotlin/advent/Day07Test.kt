package advent


import org.junit.jupiter.api.Disabled
import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {
    private val example = "data/07_ex.txt"
    private val input = "data/07_in.txt"

    private val service = Day07()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(6440, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(253910319, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent)
        assertEquals(5905, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertEquals(254083736, response)
    }

    @Test
    fun testMap(){
        val r = service.map("32T3K 765")
        val expect = Day07.Mano("32T3K", Day07.Figure.PAREJA, 765)
        assertEquals(listOf(expect), r)
    }

    @Test
    fun testFigure(){
        var r = service.calculateFigure("AABCC")
        assertEquals(r, Day07.Figure.DOBLE)
        r = service.calculateFigure("AAAAA")
        assertEquals(r, Day07.Figure.REPOKER)
        r = service.calculateFigure("AAAAB")
        assertEquals(r, Day07.Figure.POKER)
        r = service.calculateFigure("ACAAB")
        assertEquals(r, Day07.Figure.TRIO)
        r = service.calculateFigure("ABAAB")
        assertEquals(r, Day07.Figure.FULL)
        r = service.calculateFigure("ABDDE")
        assertEquals(r, Day07.Figure.PAREJA)
        r = service.calculateFigure("ABCDE")
        assertEquals(r, Day07.Figure.ALTA)
    }

    @Test
    fun testFigureWithJ(){
        var r = service.calculateFigureWithJ("AAJCC")
        assertEquals(r, Day07.Figure.FULL)
        r = service.calculateFigure("AAAAA")
        assertEquals(r, Day07.Figure.REPOKER)
        r = service.calculateFigure("AAAAB")
        assertEquals(r, Day07.Figure.POKER)
        r = service.calculateFigure("ACAAB")
        assertEquals(r, Day07.Figure.TRIO)
        r = service.calculateFigure("ABAAB")
        assertEquals(r, Day07.Figure.FULL)
        r = service.calculateFigure("ABDDE")
        assertEquals(r, Day07.Figure.PAREJA)
        r = service.calculateFigure("ABCDE")
        assertEquals(r, Day07.Figure.ALTA)
    }

}