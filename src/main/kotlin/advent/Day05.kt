package advent

import kotlin.math.pow
import kotlin.time.Duration.Companion.seconds

class Day05 {
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
        println("SeedSize = ${seeds.size}")
        val result = seeds.map { mapSeedToLocation(it, map) }
        return result.minOfOrNull { it }!!
    }

    private fun mapSeedToLocation(range: LongRange, map: Map<Pair<String, String>, List<GardenMap>>): Long{
        println("map")
        var minimum = Long.MAX_VALUE
        range.forEach {
           val result =  mapSeedToLocation(it, map)
            if (result.second < minimum){
                minimum = result.second
            }
        }
        return minimum
    }


        private fun mapSeedToLocation(input: Long, map: Map<Pair<String, String>, List<GardenMap>>): Pair<Long, Long>{
        val transforms = listOf(
            Pair("seed", "soil"),
            Pair("soil", "fertilizer"),
            Pair("fertilizer", "water"),
            Pair("water", "light"),
            Pair("light", "temperature"),
            Pair("temperature", "humidity"),
            Pair("humidity", "location"),
        )
        var current = input
        transforms.forEach {
            current = mapToNext(current, map, it)
        }
        return Pair(input, current)
    }

    private fun mapToNext(input: Long, map: Map<Pair<String, String>, List<GardenMap>>, x: Pair<String, String>): Long{
        val currentMap = map[x]!!
        val find = currentMap.filter { isInRange(input, it) }
        if (find.size > 1){
            throw Exception("Error")
        }
        return if (find.isEmpty()){
            input
        } else {
            val n = find.first()
            input + (n.dest - n.orig)
        }
    }

    private fun isInRange(value: Long, x: GardenMap): Boolean{
        val range = (x.orig..<x.orig+x.count)
        return range.contains(value)
    }

    private fun mapSeeds(line: String): List<Long>{
        return line.split("seeds: ").last().split(" ").map { it.trim().toLong() }
    }


    private fun mapSeedsSecond(line: String): List<LongRange>{
        val numbers =  line.split("seeds: ").last().split(" ").map { it.trim().toLong() }
        val rangeI = numbers.indices
        var i = 0
        val ranges: MutableList<LongRange> = mutableListOf()
        while (i < numbers.size){
            ranges.add(numbers[i]..< numbers[i] + numbers[i+1])
            i++
            i++
        }
        return ranges
    }

    private fun mapSeedToSoil(lines: List<String>): MutableMap<Pair<String, String>, List<GardenMap>> {
        val result: MutableMap<Pair<String,String>, List<GardenMap>> = mutableMapOf()
        var mapping = false
        var currentMap: Pair<String, String> = Pair("", "")
        var gardenList = mutableListOf<GardenMap>()
        lines.forEach {l ->
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

    data class GardenMap(val dest: Long, val orig: Long, val count: Long)
}