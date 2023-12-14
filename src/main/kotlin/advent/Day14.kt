package advent


class Day14 {

    fun execute01(input: String): Long {
        val x = map(input)
        val y = tillN(x)
        return points(y)
    }

    fun execute02(input: String): Long {
        val x = map(input)
        val z = cicle(x, 1000_000_000)
        return points(z)
    }

    private fun cicle(map: List<List<Int>>, n: Int): List<List<Int>> {
        var z: List<List<Int>> = map
        val cache =  mutableMapOf<List<List<Int>>, Int>()
        var cacheOld = 0
        var cacheNew = 0
        for (it in 1..n) {
            z = cicle(z)
            if(cache.contains(z)){
                cacheOld = cache[z]!!
                cacheNew = it
                break
            } else {
                cache[z] = it
            }
        }
        println("Find exact at: $cacheOld $cacheNew")
        val newN = n - cacheOld
        val mod = newN.mod(cacheNew - cacheOld)
        println("Going to execute the last $mod")
        z = cicleOld(z, mod)
        return z
    }
    private fun cicleOld(map: List<List<Int>>, n: Int): List<List<Int>> {
        var z: List<List<Int>> = map
        for (it in 1..n) {
            z = cicle(z)
        }
        return z
    }

    private fun cicle(map: List<List<Int>>): List<List<Int>> {
        var r = tillN(map)
        r = tillW(r)
        r = tillS(r)
        r = tillE(r)
        return r
    }

    private fun tillN(input: List<List<Int>>): List<List<Int>> {
        val response = prepareField(input)
        val yIndex = input.indices
        val xIndex = input[0].indices
        yIndex.forEach { y ->
            xIndex.forEach { x ->
                if (input[y][x] == 2) {
                    // move to top
                    var subY = y
                    var cont = true
                    do {
                        subY--
                        if (subY == -1 || response[subY][x] == 1 || response[subY][x] == 2) {
                            cont = false
                        }
                    } while (cont)
                    response[subY + 1][x] = 2
                }
            }
        }
        return response
    }

    private fun tillW(input: List<List<Int>>): List<List<Int>> {
        val response = prepareField(input)
        val yIndex = input.indices
        val xIndex = input[0].indices
        xIndex.forEach { x ->
            yIndex.forEach { y ->
                if (input[y][x] == 2) {
                    // move to west
                    var subX = x
                    var cont = true
                    do {
                        subX--
                        if (subX == -1 || response[y][subX] == 1 || response[y][subX] == 2) {
                            cont = false
                        }
                    } while (cont)
                    response[y][subX + 1] = 2
                }
            }
        }
        return response
    }

    private fun tillS(input: List<List<Int>>): List<List<Int>> {
        val response = prepareField(input)
        val yIndex = input.indices.reversed()
        val xIndex = input[0].indices
        val yMax = input.size - 1
        yIndex.forEach { y ->
            xIndex.forEach { x ->
                if (input[y][x] == 2) {
                    var subY = y
                    var cont = true
                    do {
                        subY++
                        if (subY > yMax || response[subY][x] == 1 || response[subY][x] == 2) {
                            cont = false
                        }
                    } while (cont)
                    response[subY - 1][x] = 2
                }
            }
        }
        return response
    }

    private fun tillE(input: List<List<Int>>): List<List<Int>> {
        val response = prepareField(input)
        val yIndex = input.indices
        val xIndex = input[0].indices.reversed()
        val xMax = input[0].size - 1
        xIndex.forEach { x ->
            yIndex.forEach { y ->
                if (input[y][x] == 2) {
                    var subX = x
                    var cont = true
                    do {
                        subX++
                        if (subX > xMax || response[y][subX] == 1 || response[y][subX] == 2) {
                            cont = false
                        }
                    } while (cont)
                    response[y][subX - 1] = 2
                }
            }
        }
        return response
    }



    private fun prepareField(input: List<List<Int>>): MutableList<MutableList<Int>> {
        val response = mutableListOf<MutableList<Int>>()
        val yIndex = input.indices
        val xIndex = input[0].indices
        yIndex.forEach { y ->
            var line = mutableListOf<Int>()
            xIndex.forEach { x ->
                if (input[y][x] == 1) {
                    line.add(1)
                } else {
                    line.add(0)
                }
            }
            response.add(line)
        }
        return response
    }

    fun points(map: List<List<Int>>): Long {
        var yIndex = map.indices
        var xIndex = map[0].indices
        var ySize = map.size

        var sum = 0L
        yIndex.forEach { y ->
            xIndex.forEach { x ->
                if (map[y][x] == 2) {
                    sum += ySize - y
                }
            }
        }
        return sum
    }

    fun map(input: String): List<List<Int>> {
        return input.lines().map { l ->
            l.map {
                if (it == 'O') {
                    2
                } else if (it == '#') {
                    1
                } else if (it == '.') {
                    0
                } else {
                    TODO()
                }
            }
        }
    }


    fun printBoard(map: List<List<Int>>) {
        map.forEach {
            it.forEach { c ->
                if (c == 0) {
                    print('.')
                } else if (c == 1) {
                    print('#')
                } else if (c == 2) {
                    print('O')
                } else {
                    TODO()
                }
            }
            println()
        }
    }

}
