package advent


class Day15 {

    fun execute01(input: String): Long {
        return input.split(",").sumOf { hash(it) }
    }

    fun execute02(input: String): Long {
        return 0
    }

    fun hash(value: Long, current:Long = 0):Long{
        var r = current
        r += value
        r *= 17
        r = r.mod(256L)
        return r
    }

    fun hash(value: Char, current: Long = 0):Long{
        return hash(value.code.toLong(), current)
    }

    fun hash(value: String):Long{
        var r = 0L
        value.forEach {
            r = hash(it, r)
        }

        return r

    }

}
