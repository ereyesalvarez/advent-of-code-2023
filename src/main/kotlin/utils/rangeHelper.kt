package utils

fun calculateIntersectionOfA(a: LongRange, b: LongRange): List<Pair<LongRange, Boolean>> {
    val result: MutableList<Pair<LongRange, Boolean>> = mutableListOf()
    if (a.first < b.first && a.last < b.first){
        return listOf(Pair(a, false))
    }
    if (a.first > b.last){
        return listOf(Pair(a, false))
    }
    if (a.first >= b.first && a.last <= b.last){
        return listOf(Pair(a, true))
    }
    if (a.first < b.first){
        result.add(Pair(a.first..<b.first, false))
        return if (a.last < b.last){
            result.add(Pair(b.first..a.last, true))
            result
        } else {
            result.add(Pair(b.first..b.last, true))
            result.add(Pair(b.last +1 .. a.last, false))
            result
        }
    }
    result.add(Pair(a.first..b.last, true))
    result.add(Pair(b.last+1..a.last, false))
    return result
}