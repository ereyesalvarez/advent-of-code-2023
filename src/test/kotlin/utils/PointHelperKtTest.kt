package utils

import model.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointHelperKtTest {

    @Test
    fun printBoardTest() {
        val board = listOf(
            listOf('a', 'b'),
            listOf('c', 'd')
        )
        printBoard(board)
    }

    @Test
    fun getAdjacentTest(){
        var response = getAdjacent(Point(0,0))
        var expected = listOf(Point(0,1), Point(1,0), Point(1,1),)
        assertThat(response).containsAll(expected)
        response = getAdjacent(Point(1,1))
        expected = listOf(Point(0,0),  Point(1,0),Point(2,0), Point(2,1), Point(2,2), Point(1,2), Point(0, 2), Point(0,1))
        assertThat(expected).hasSize(8)
        assertThat(response).containsExactlyInAnyOrderElementsOf(expected)

        response = getAdjacent(Point(1,1), 1,1)
        expected = listOf(Point(0,0),  Point(1,0), Point(0,1))
        assertThat(expected).hasSize(3)
        assertThat(response).containsExactlyInAnyOrderElementsOf(expected)

    }
}