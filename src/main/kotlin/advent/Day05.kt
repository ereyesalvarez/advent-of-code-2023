package advent

import utils.calculateIntersectionOfA

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
        val seeds = mapSeeds(lines.first()).map { it..it }
        val map = mapTransformations(lines)
        val result = transformToLocation(seeds, map)
        return result.minOf { it.first }
    }

    fun execute02(input: String): Long {
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
                val intersection = calculateIntersectionOfA(c, tr.first)
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

}