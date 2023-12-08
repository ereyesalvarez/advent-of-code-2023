package advent

class Day07 {

    private fun resolve(puzzle: List<Mano>, comparator: Comparator<Mano>): Long {
        val grouped = puzzle.groupBy { it.figure }
        var points = 1L
        var x = 0L
        Figure.entries.reversed().forEach {
            val hands = grouped.getOrDefault(it, listOf())
            x += calculatePoints(hands, points, comparator)
            points += hands.size
        }
        return x
    }

    fun execute01(input: String): Long {
        val puzzle = map(input)
        val order = "AKQJT98765432"
        return resolve(puzzle, getHandComp(order))
    }

    fun execute02(input: String): Long {
        val puzzle = map2(input)
        val order = "AKQT98765432J"
        return resolve(puzzle, getHandComp(order))

    }

    private fun calculatePoints(game: List<Mano>, startPoints: Long = 1, comparator: Comparator<Mano>): Long {
        var point = startPoints
        val sorted = game.sortedWith(comparator).reversed()
        return sorted.sumOf {
            val x = it.points * point
            point++
            x
        }
    }


    private fun getHandComp(cardOrder: String): Comparator<Mano>{
        return Comparator { manoA, manoB ->
            var result = 0
            manoA.cards.indices.forEach {
                if (manoA.cards[it] == manoB.cards[it]) {
                }
                if (result == 0) {
                    result = getCharComparator(cardOrder).compare(manoA.cards[it], manoB.cards[it])
                }
            }
            result
        }
    }
    private fun getCharComparator(cardOrder: String) = Comparator<Char> { char1, char2 ->
        cardOrder.indexOf(char1) - cardOrder.indexOf(char2)
    }

    fun map(input: String): List<Mano> {
        return input.lines().map {
            val split = it.split(" ")
            Mano(split.first(), calculateFigure(split.first()), split.last().toLong())
        }
    }

    private fun map2(input: String): List<Mano> {
        return input.lines().map {
            val split = it.split(" ")
            Mano(split.first(), calculateFigureWithJ(split.first()), split.last().toLong())
        }
    }

    fun calculateFigureWithJ(line: String): Figure {
        val jokers = line.count { it == 'J' }
        if (jokers == 4 || jokers == 5){
            return Figure.REPOKER
        }
        val grouped = line.replace("J", "").groupBy { it }.map { it.value.size }.sorted()
        val max = grouped.max()

        if (jokers == 3){
            if (max == 2){
                return Figure.REPOKER
            }
            return Figure.POKER
        }
        if (jokers == 2){
            if (max == 3){
                return Figure.REPOKER
            }
            if (max == 2){
                return Figure.POKER
            }
            return Figure.TRIO
        }
        if (jokers == 1){
            if (max == 4){
                return Figure.REPOKER
            }
            if (max == 3){
                return Figure.POKER
            }
            if (max == 2){
                if (grouped == listOf(2,2)){
                    return Figure.FULL
                }
                return Figure.TRIO
            }
            return Figure.PAREJA
        }

        if (max == 5) {
            return Figure.REPOKER
        }
        if (max == 4) {
            return Figure.POKER
        }
        if (max == 3 && grouped.contains(2)) {
            return Figure.FULL
        }
        if (max == 3) {
            return Figure.TRIO
        }
        if (max == 2 && grouped == listOf(1, 2, 2)) {
            return Figure.DOBLE
        }
        if (max == 2) {
            return Figure.PAREJA
        }
        return Figure.ALTA
    }


    fun calculateFigure(line: String): Figure {
        val grouped = line.groupBy { it }.map { it.value.size }.sorted()
        val max = grouped.max()
        if (max == 5) {
            return Figure.REPOKER
        }
        if (max == 4) {
            return Figure.POKER
        }
        if (max == 3 && grouped.contains(2)) {
            return Figure.FULL
        }
        if (max == 3) {
            return Figure.TRIO
        }
        if (max == 2 && grouped.equals(listOf(1, 2, 2))) {
            return Figure.DOBLE
        }
        if (max == 2) {
            return Figure.PAREJA
        }
        return Figure.ALTA
    }

    data class Mano(val cards: String, val figure: Figure, val points: Long)

    enum class Figure {
        REPOKER, POKER, FULL, TRIO, DOBLE, PAREJA, ALTA
    }
}
