package model

data class Point(val x: Int = 0, val y: Int = 0){
    fun moveL(): Point {
        return Point(x-1, y)
    }
    fun moveR(): Point {
        return Point(x+1, y)
    }
    fun moveT(): Point {
        return Point(x, y-1)
    }
    fun moveB(): Point {
        return Point(x, y+1)
    }
}