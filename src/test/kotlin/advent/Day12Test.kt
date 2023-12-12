package advent


import model.Point
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Disabled
import utils.getFileAsText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {
    private val example = "data/12_ex.txt"
    private val input = "data/12_in.txt"

    private val service = Day12()

    @Test
    fun example1() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute01(puzzleContent)
        assertEquals(21, response)
    }

    @Test
    fun exercise1() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute01(puzzleContent)
        println(response)
        assertEquals(7792, response)
    }

    @Test
    fun example2() {
        val puzzleContent = getFileAsText(example)
        val response = service.execute02(puzzleContent)
        assertEquals(525152, response)
    }

    @Test
    fun exercise2() {
        val puzzleContent = getFileAsText(input)
        val response = service.execute02(puzzleContent)
        println(response)
        assertNotEquals(28366205725, response)
        assertEquals(0, response)
    }
//
//    @Test
//    fun calculateInput() {
//        assertEquals(listOf(3, 2, 1), service.calculateInt(".###.##.#..."))
//        assertEquals(listOf(1,1,3), service.calculateInt("#.#.###"))
//    }
//
//    @Test
//    fun mapToList() {
//        assertEquals(
//            listOf(
//                Pair('.', 1),
//                Pair('#', 3),
//                Pair('.', 1),
//                Pair('#', 2),
//                Pair('?', 1),
//                Pair('#', 1),
//                Pair('.', 3),
//            ), service.mapToList(".###.##?#...")
//        )
//    }
}