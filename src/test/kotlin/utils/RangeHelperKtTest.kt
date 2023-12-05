package utils

import kotlin.test.Test

class RangeHelperKtTest {
    @Test
    fun calculateIntersection() {
        val a = 79L..79
        val b = 59L..97
        val result = calculateIntersectionOfA(a, b)
        val expect = listOf(Pair(79..79L, true))
        kotlin.test.assertEquals(expect, result)
    }

    @Test
    fun calculateIntersection2() {
        val a = 1L..10
        val b = 11L..100
        val result = calculateIntersectionOfA(a, b)
        val expect = listOf(Pair(1..10L, false))
        kotlin.test.assertEquals(expect, result)
    }
}