package advent

import utils.transpose


class Day13 {

    fun execute01(input: String): Long {
        return map(input).map { compareSymmetryB(it.toList()) }.sumOf { covert(it) }.toLong()
    }

    fun execute02(input: String): Long {
        return map(input).sumOf { covert(danceThePoints(it.toList())).toLong() }
    }

    private fun danceThePoints(board: List<List<Int>>): Pair<Int, Char> {
        val prevSim = compareSymmetryB(board)
        val yIndex = board.indices
        val xIndex = board.first().indices
        var result = Pair(0, 'h')
        yIndex.forEach { y ->
            xIndex.forEach { x ->
                val boardCopy = board.map { it.toMutableList() }.toMutableList()
                if (boardCopy[y][x] == 0) {
                    boardCopy[y][x] = 1
                } else {
                    boardCopy[y][x] = 0
                }
                try {
                    result = compareSymmetryB(boardCopy, prevSim)
                } catch (_: NoSuchElementException) {
                }
            }
        }
        if (result.first == 0) {
            throw RuntimeException("Result can't be 0")
        }
        return result
    }

    private fun compareSymmetryB(
        board: List<List<Int>>, filter: Pair<Int, Char>? = null
    ): Pair<Int, Char> {
        var h = checkParallel(board)
        if (filter != null && filter.second == 'h') {
            h = h.filter { it != filter.first }
        }
        if (h.isNotEmpty()) {
            return Pair(h.first(), 'h')
        }

        val table = transpose(board)
        var n = checkParallel(table)
        if (filter != null && filter.second == 'v') {
            n = n.filter { it != filter.first }
        }
        return Pair(n.first(), 'v')
    }

    private fun checkParallel(board: List<List<Int>>): List<Int> {
        var x = (1..<board.first().size).toList()
        board.forEach {
            val n = getParallelCandidates(it)
            x = x.filter { x1 -> n.contains(x1) }
        }
        return x
    }

    fun getParallelCandidates(input: List<Int>): List<Int> {
        val response = mutableListOf<Int>()
        val x = (1..<input.size)
        x.forEach {
            val toEv = if (it > input.size / 2) {

                input.subList(it - (input.size - it), input.size)
            } else {
                input.subList(0, it * 2)
            }
            if (isParallel(toEv)) {
                response.add(it)
            }
        }
        return response
    }

    fun isParallel(input: List<Int>): Boolean {
        var start = 0
        var end = input.size - 1
        while (start < end) {
            if (input[start] != input[end]) {
                return false
            }
            start++
            end--
        }
        return true
    }

    private fun covert(it: Pair<Int, Char>): Int {
        return if (it.second == 'h') {
            it.first
        } else {
            it.first * 100
        }
    }

    fun map(input: String): List<List<List<Int>>> {
        val response = mutableListOf<MutableList<MutableList<Int>>>()
        var board = mutableListOf<MutableList<Int>>()
        input.lines().forEach { l ->
            if (l.isEmpty()) {
                response.add(board)
                board = mutableListOf()
            } else {
                val line = mutableListOf<Int>()
                l.forEach { c ->
                    if (c == '.') {
                        line.add(0)
                    } else {
                        line.add(1)
                    }
                }
                board.add(line)
            }
        }
        return response
    }

}
