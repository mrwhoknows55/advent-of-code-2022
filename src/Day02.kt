import java.io.File

fun main() {
    val input = File("src/day02_input.txt").readText().split("\n")
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>): Int {
    var score = 0
    input.forEach {
        val oppMove = Move.getMoveObj(it[0]) ?: return@part1 (score)
        val myMove = Move.getMoveObj(it[2]) ?: return@part1 (score)
        score += myMove.score
        when {
            myMove.winsTo == oppMove.score -> score += 6
            myMove.score == oppMove.score -> score += 3
            myMove.losesTo == oppMove.score -> score += 0
        }
    }
    return score
}

fun part2(input: List<String>): Int {
    var score = 0
    input.forEach {
        val oppMove = Move.getMoveObj(it[0]) ?: return@part2 (score)
        val myMove = Move.getMoveObj(it[2]) ?: return@part2 (score)
        when (myMove) {
            is Move.Rock -> {
                score += oppMove.winsTo
                score += 0
            }

            is Move.Paper -> {
                score += oppMove.score
                score += 3
            }

            is Move.Scissors -> {
                score += oppMove.losesTo
                score += 6
            }
        }
    }
    return score
}

sealed class Move(
    val score: Int,
    val winsTo: Int,
    val losesTo: Int,
) {
    object Rock : Move(1, 3, 2)

    object Paper : Move(2, 1, 3)

    object Scissors : Move(3, 2, 1)
    companion object {
        fun getMoveObj(moveChar: Char): Move? {
            return when (moveChar) {
                'A' -> Rock
                'X' -> Rock
                'B' -> Paper
                'Y' -> Paper
                'C' -> Scissors
                'Z' -> Scissors
                else -> null
            }
        }
    }
}
