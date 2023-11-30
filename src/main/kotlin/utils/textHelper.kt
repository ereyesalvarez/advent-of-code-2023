package utils

fun getAsListOfInt(input: String): List<Int?> {
    return input.lines().map {
        if (it.isBlank()) {
            null
        } else {
            it.toInt()
        }
    }
}

fun getAsListOfChars(input: String): List<Char>{
    return input.toCharArray().toList()
}