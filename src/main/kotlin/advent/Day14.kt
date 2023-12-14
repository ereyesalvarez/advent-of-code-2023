package advent


class Day14 {

    fun execute01(input: String): Long {
        val x = map(input)
        printBoard(x)
        println()
        val y = tillN(x)
        return points(y)
    }

    fun execute02(input: String): Long {
        val x = map(input)
        printBoard(x)
        println()
        val y = tillN(x)
        return points(y)
    }



    fun points(map: List<List<Int>>): Long{
        var yIndex = map.indices
        var xIndex = map[0].indices
        var ySize = map.size

        var sum = 0L
        yIndex.forEach {y ->
            xIndex.forEach {x->
                if(map[y][x] == 2){
                    sum += ySize - y
                }
            }
        }
        return sum
    }
    fun map(input: String): List<List<Int>>{
        return input.lines().map {l ->
            l.map {
                if (it == 'O'){
                    2
                } else if (it == '#'){
                    1
                } else if (it == '.'){
                    0
                } else {
                    TODO()
                }
            }
        }
    }
    fun tillN(input: List<List<Int>>): List<List<Int>>{
        var mut = input.map { it.toMutableList() }.toMutableList()
        var response = mutableListOf<MutableList<Int>>()
        var xIndex = mut[0].indices
        mut.indices.forEach { y ->
            var line = mutableListOf<Int>()
            xIndex.forEach { x ->
                if (input[y][x] == 1){
                    line.add(1)
                } else {
                    line.add(0)
                }
            }
            response.add(line)
        }
        mut.indices.forEach { y ->
            xIndex.forEach { x ->
                if (input[y][x] == 2){
                    // move to top
                    var subY = y
                    var cont = true
                    do {
                        subY--
                        if (subY == -1 || response[subY][x] == 1 || response[subY][x] == 2){
                            cont = false
                        }
                    } while (cont)
                    response[subY + 1][x] = 2
                }
            }
        }
        printBoard(response)
        return response
    }

    fun printBoard(map: List<List<Int>>){
        map.forEach {
            it.forEach { c->
                if (c == 0){
                    print('.')
                } else  if (c == 1){
                    print('#')
                } else  if (c == 2){
                    print('O')
                } else {
                    TODO()
                }
             }
            println()
        }
    }

}
