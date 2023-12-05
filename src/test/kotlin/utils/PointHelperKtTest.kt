package utils

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class PointHelperKtTest {

    @Test
    fun printBoard() {
        val board = listOf(
            listOf('a', 'b'),
            listOf('c', 'd')
        )
        utils.printBoard(board)
    }
}