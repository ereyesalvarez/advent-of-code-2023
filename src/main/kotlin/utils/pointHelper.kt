package utils

import model.Point

fun printBoard(board: List<List<Char>>){
    board.forEach {
        it.forEach {c ->
            print(" $c ")
        }
        println()
    }
}

fun getAdjacent(point: Point, xMax: Int = Int.MAX_VALUE, yMax: Int = Int.MAX_VALUE): MutableList<Point> {
    val points: MutableList<Point> = mutableListOf()
    if (point.x != 0){
        points.add(point.moveL())
        if (point.y != 0){
            points.add(point.moveL().moveT())
        }
        if (point.y != yMax){
            points.add(point.moveL().moveB())
        }
    }
    if (point.x != xMax){
        points.add(point.moveR())
        if (point.y != 0){
            points.add(point.moveR().moveT())
        }
        if (point.y != yMax){
            points.add(point.moveR().moveB())
        }
    }
    if (point.y != 0){
        points.add(point.moveT())
    }
    if (point.y != yMax){
        points.add(point.moveB())
    }
    return points
}