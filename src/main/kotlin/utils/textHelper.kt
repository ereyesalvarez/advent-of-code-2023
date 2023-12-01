package utils

fun getAsListOfInt(input: String): List<Int> {
    return getAsListOfNullableInt(input).filterNotNull()
}
fun getAsListOfNullableInt(input: String): List<Int?> {
    return input.lines().map { it.toIntOrNull() }
}

fun getAsListOfChars(input: String): List<Char> {
    return input.toCharArray().toList()
}

fun mapStringToDigit(input: String): Int {
    return when (input) {
        "one" -> 1
        "two" -> 2
        "three" -> 3
        "four" -> 4
        "five" -> 5
        "six" -> 6
        "seven" -> 7
        "eight" -> 8
        "nine" -> 9
        else -> {
            input.toInt()
        }
    }
}