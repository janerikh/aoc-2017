import java.io.File

val LIST_SIZE = 256

fun main(args: Array<String>) {

    val input = File(ClassLoader.getSystemResource("input.txt").file)
            .readLines()

    val inpu1Part1 = input
            .map { s -> s.split(",") }[0]
            .map { s -> s.toInt() }

    val part1 = process(inpu1Part1, 1)
    println("part 1: " + (part1[0] * part1[1]))

    val inputPart2 = input
            .map { s -> s.map { c -> c.toInt() } }[0]

    val additionToList = listOf(17, 31, 73, 47, 23)
    val inputPart2WithAddition = mutableListOf<Int>()
    inputPart2WithAddition.addAll(inputPart2)
    inputPart2WithAddition.addAll(additionToList)

    val rounds = 64
    val sparseHash = process(inputPart2WithAddition, rounds)
    val denseHash = findDenseHash(sparseHash)

    val part2Hash = denseHash.map { i: Int -> java.lang.String.format("%02X", i).toLowerCase() }
            .reduce {acc, s -> acc + s }
    println("part 2 hash $part2Hash")
}

fun findDenseHash(sparseHash: List<Int>): List<Int> {
    return sparseHash.windowed(16, 16)
            .map { list ->
                list.reduce {i, j -> i.xor(j)}
            }
}

fun process(input: List<Int>, rounds: Int): List<Int> {
    var circList = buildCircList(LIST_SIZE)

    var currentPos = 0
    var skipSize = 0

    for (j in 1..rounds) {
        for (i in 0..input.size - 1) {
            val len = input[i]
            val newlist = reversePartOfCircularList(circList, currentPos, len)
            currentPos = (currentPos + len + skipSize) % circList.size
            skipSize++
            circList = newlist
        }
    }

    return circList
}

fun buildCircList(size: Int): List<Int> {
    return (0..size-1).toList()
}

fun reversePartOfCircularList(list: List<Int>, fromIndex: Int, len: Int): List<Int> {
    val newList = list.toMutableList()
    var listToReverse = mutableListOf<Int>()
    for (i in 0..len-1) {
        val pos = (fromIndex+i)%list.size
        listToReverse.add(list[pos])
    }
    val reversedList = listToReverse.reversed().toMutableList()

    for (i in 0..len-1) {
        val pos = (fromIndex+i)%list.size
        newList[pos] = reversedList[i]
    }
    return newList
}
