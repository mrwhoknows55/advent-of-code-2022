import java.io.File
import java.util.Stack

fun main() {
    val inputPair = getInputPair("src/day05_input.txt")
    part1(inputPair)
}

fun part1(inputPair: Pair<List<Stack<Char>>, List<Triple<Int, Int, Int>>>) {
    val stacks = inputPair.first
    val inputs = inputPair.second
    inputs.forEach {
        for (i in 0 until it.first) {
            if (stacks.size >= it.second) {
                val selected = stacks[it.second - 1]
                if (stacks.size >= it.third && selected.isNotEmpty()) {
                    stacks[it.third - 1].push(selected.peek())
                    selected.pop()
                }
            }
        }
    }
    val ans = stacks.joinToString(separator = "") {
        it.peek().toString()
    }
    println(ans)
}

fun getInputPair(fileName: String): Pair<List<Stack<Char>>, List<Triple<Int, Int, Int>>> {
    val fileInput = File(fileName).readText().split("\n\n")
    val other = fileInput[1].split("\n")
    val inputs = other.map {
        val words = it.split(" ")
        Triple(words[1].toInt(), words[3].toInt(), words[5].toInt())
    }
    val matrix = fileInput.first()
    var str = ""
    matrix.forEachIndexed { i, c ->
        if (i % 4 == 0) {
            str += "["
        } else if (i % 2 == 0) {
            str += "]"
        } else {
            str += c
        }
    }
    val lets = str.replace("[ ]", "-").replace("[", "").replace("]", "").replace(" ", "").split("\n").map {
        it.split("").filter { it.isNotBlank() }
    }
    val length = lets.last().size
    val letters = lets.dropLast(1)
    val stacks = mutableListOf<Stack<Char>>()
    for (i in 0 until length) {
        stacks.add(Stack())
        for (j in 0 until length) {
            if (letters.size > j && letters[j].size > i) letters[j][i].let {
                if (it != "-") stacks[i].add(it.first())
            }
        }
        stacks[i].reverse()
    }
    return Pair(stacks.toList(), inputs)
}
