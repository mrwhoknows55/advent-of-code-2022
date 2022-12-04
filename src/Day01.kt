import java.io.File

fun main() {
    fun parseInput(input: String) = input.split("\n\n").map { elf ->
        elf.lines().map { it.toInt() }
    }

    fun List<List<Int>>.topNElves(n: Int): Int = this.map { it.sum() }.sortedDescending().take(n).sum()

    fun part1(input: String): Int = parseInput(input).topNElves(1)
    fun part2(input: String): Int = parseInput(input).topNElves(3)

    val input = File("src/day01_input.txt").readText()
    println(part1(input))
    println(part2(input))
}
