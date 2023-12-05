package advent

import kotlin.math.pow
import kotlin.time.Duration.Companion.seconds

class Day05 {
    private val steps = listOf(
        Pair("seed", "soil"),
        Pair("soil", "fertilizer"),
        Pair("fertilizer", "water"),
        Pair("water", "light"),
        Pair("light", "temperature"),
        Pair("temperature", "humidity"),
        Pair("humidity", "location"),
    )

    fun execute01(input: String): Long {
        val lines = input.lines()
        val seeds = mapSeeds(lines.first())
        val map = mapSeedToSoil(lines)
        val result = seeds.map { mapSeedToLocation(it, map) }

        return result.minOfOrNull { it.second }!!
    }

    fun execute02(input: String): Long {
        val lines = input.lines()
        val seeds = mapSeedsSecond(lines.first())
        val map = mapSeedToSoil(lines)
        val result = seeds.map { mapSeedToLocation(it, map) }
        return result.minOfOrNull { it }!!
    }

    fun execute01B(input: String): Long {
        val lines = input.lines()
        val seeds = mapSeeds(lines.first()).map { it..it }
        val map = mapTransformations(lines)
        val result = transformToLocation(seeds, map)
        return result.minOf { it.first }
    }

    fun execute02B(input: String): Long {
        val lines = input.lines()
        val seeds = mapSeedsSecond(lines.first())
        val map = mapTransformations(lines)
        val result = transformToLocation(seeds, map)
        return result.minOf { it.first }
    }

    private fun transformToLocation(input: List<LongRange>, map: Map<Pair<String, String>, List<Pair<LongRange, Long>>>): List<LongRange> {
        var partial = input
        steps.forEach {
            partial = calculateNext(partial, map[it]!!)
        }
        return partial
    }

    fun calculateNext(inputRange: List<LongRange>, transformations: List<Pair<LongRange, Long>>): List<LongRange> {
        val result: MutableList<LongRange> = mutableListOf()
        var rangesToExplore = inputRange
        transformations.forEach { tr ->
            val pendingRanges = mutableListOf<LongRange>()
            rangesToExplore.forEach { c ->
                val intersection = calculateIntersection(c, tr.first)
                intersection.forEach {
                    if (it.second){
                        val newFrom = it.first.first + tr.second
                        val newTo = it.first.last + tr.second
                        result.add(newFrom..newTo)
                    } else {
                        pendingRanges.add(it.first)
                    }
                }
            }
            rangesToExplore = pendingRanges
        }
        if (rangesToExplore.isNotEmpty()){
            result.addAll(rangesToExplore)
        }
        return result
    }

    fun calculateIntersection(a: LongRange, b: LongRange): List<Pair<LongRange, Boolean>> {
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

    private fun mapSeedToLocation(range: LongRange, map: Map<Pair<String, String>, List<GardenMap>>): Long {
        var minimum = Long.MAX_VALUE
        range.forEach {
            val result = mapSeedToLocation(it, map)
            if (result.second < minimum) {
                minimum = result.second
            }
        }
        return minimum
    }


    private fun mapSeedToLocation(input: Long, map: Map<Pair<String, String>, List<GardenMap>>): Pair<Long, Long> {
        var current = input
        steps.forEach {
            current = mapToNext(current, map, it)
        }
        return Pair(input, current)
    }

    private fun mapToNext(input: Long, map: Map<Pair<String, String>, List<GardenMap>>, x: Pair<String, String>): Long {
        val currentMap = map[x]!!
        val find = currentMap.filter { isInRange(input, it) }
        if (find.size > 1) {
            throw Exception("Error")
        }
        return if (find.isEmpty()) {
            input
        } else {
            val n = find.first()
            input + (n.dest - n.orig)
        }
    }

    private fun isInRange(value: Long, x: GardenMap): Boolean {
        val range = (x.orig..<x.orig + x.count)
        return range.contains(value)
    }

    private fun mapSeeds(line: String): List<Long> {
        return line.split("seeds: ").last().split(" ").map { it.trim().toLong() }
    }


    private fun mapSeedsSecond(line: String): List<LongRange> {
        val numbers = line.split("seeds: ").last().split(" ").map { it.trim().toLong() }
        var i = 0
        val ranges: MutableList<LongRange> = mutableListOf()
        while (i < numbers.size) {
            ranges.add(numbers[i]..<numbers[i] + numbers[i + 1])
            i++
            i++
        }
        return ranges
    }

    private fun mapSeedToSoil(lines: List<String>): MutableMap<Pair<String, String>, List<GardenMap>> {
        val result: MutableMap<Pair<String, String>, List<GardenMap>> = mutableMapOf()
        var mapping = false
        var currentMap: Pair<String, String> = Pair("", "")
        var gardenList = mutableListOf<GardenMap>()
        lines.forEach { l ->
            if (l.contains(" map:")) {
                mapping = true
                val data = l.split(" map:").first().split("-")
                currentMap = Pair(data.first(), data.last())
            } else if (mapping) {
                if (l.isBlank()) {
                    mapping = false
                    result[currentMap] = gardenList
                    gardenList = mutableListOf()
                } else {
                    val x = l.split(" ")
                    gardenList.add(GardenMap(x[0].toLong(), x[1].toLong(), x[2].toLong()))
                }
            }
        }
        return result
    }


    private fun mapTransformations(lines: List<String>): MutableMap<Pair<String, String>, List<Pair<LongRange, Long>>> {
        var mapping = false
        var currentMap: Pair<String, String> = Pair("", "")
        var transformations = mutableListOf<Pair<LongRange, Long>>()
        val transformationsMap: MutableMap<Pair<String, String>, List<Pair<LongRange, Long>>> = mutableMapOf()
        lines.forEach { l ->
            if (l.contains(" map:")) {
                mapping = true
                val data = l.split(" map:").first().split("-")
                currentMap = Pair(data.first(), data.last())
            } else if (mapping) {
                if (l.isBlank()) {
                    mapping = false
                    transformationsMap[currentMap] = transformations
                    transformations = mutableListOf()
                } else {
                    val x = l.split(" ")
                    val dest = x[0].toLong()
                    val orig = x[1].toLong()
                    val count = x[2].toLong()
                    transformations.add(Pair(orig..<orig + count, dest - orig))
                }
            }
        }
        return transformationsMap
    }

    data class GardenMap(val dest: Long, val orig: Long, val count: Long)
}