package utils

 fun <T> transpose(table: List<List<T>>): List<List<T>> {
        val ret: MutableList<List<T>> = ArrayList()
        val N = table[0].size
        for (i in 0..<N) {
            val col: MutableList<T> = ArrayList()
            for (row in table) {
                col.add(row[i])
            }
            ret.add(col)
        }
        return ret
    }
