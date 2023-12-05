package utils

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
}