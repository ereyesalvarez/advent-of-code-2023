package advent


class Day15 {

    fun execute01(input: String): Long {
        return input.split(",").sumOf { hash(it) }
    }

    data class Order(val string: String, val order: Char, val number: Int, val hash: Int)

    fun execute02(input: String): Long {
        val map = map(input)
        play02(map)
        return 0
    }

    fun initialize(): MutableMap<Int, Map<String, Int>> {
        return (0..255).associateWith { mapOf<String, Int>() }.toMutableMap()
    }

    fun play02(orders: List<Order>): Long {
        val list = initialize()
        orders.forEach { o ->
            if (o.order == '-') {
                val inner = list[o.hash]!!.toMutableMap()
                if (inner.contains(o.string)) {
                    inner.remove(o.string)
                    list[o.hash] = inner
                    println("BoxR ${o.hash} -> $inner")
                }
            } else if (o.order == '=') {
                val inner = list[o.hash]!!.toMutableMap()
                inner[o.string] = o.number
                list[o.hash] = inner
                println("BoxA ${o.hash} -> $inner")
            } else {
                TODO()
            }
        }
        var sum = 0L
        list.forEach {
            if (it.value.isNotEmpty()){
                println("calculating box ${it.key}")
                var index = 1
                it.value.forEach { lens ->
                    println("Calculating ${lens.value}")
                    var r = calculate(it.key + 1, index, lens.value)
                    println(r)
                    sum += r
                    index++
                }
            }
        }
        println(sum)
        return sum
    }

    fun calculate(box: Int, slot: Int, focal: Int): Long {
        return box * slot * focal.toLong()
    }

    fun map(input: String): List<Order> {
        return input.split(",").map { l ->
                if (l.contains("=")) {
                    var string = l.substring(0, l.length - 2)
                    var order = l.substring(l.length - 2).first()
                    var number = l.substring(l.length - 1).toInt()
                    var hash = hash(string)
                    return@map Order(string, order, number, hash.toInt())
                } else if (l.contains("-")) {
                    var string = l.substring(0, l.length - 1)
                    var order = '-'
                    var number = 0
                    var hash = hash(string)
                    return@map Order(string, order, number, hash.toInt())
                }
                TODO()
            }
    }

    fun hash(value: Long, current: Long = 0): Long {
        var r = current
        r += value
        r *= 17
        r = r.mod(256L)
        return r
    }

    fun hash(value: Char, current: Long = 0): Long {
        return hash(value.code.toLong(), current)
    }

    fun hash(value: String): Long {
        var r = 0L
        value.forEach {
            r = hash(it, r)
        }

        return r

    }

}
