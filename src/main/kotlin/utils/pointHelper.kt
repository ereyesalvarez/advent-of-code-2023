package utils

fun printBoard(board: List<List<Char>>){
    board.forEach {
        it.forEach {c ->
            print(" $c ")
        }
        println()
    }
}