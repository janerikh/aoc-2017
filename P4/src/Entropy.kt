import java.io.File

fun main(args: Array<String>) {

    val pphrases = File(ClassLoader.getSystemResource("input.txt").file)
            .readLines()
            .map { it.split(" ") }

    part1(pphrases)
    part2(pphrases)
}

fun part2(pphrases: List<List<String>>) {
    println(
    pphrases.map { list ->
        list.map { s -> sortLowerCase(s) } }
            .filter { list ->  list.distinct().size == list.size }
            .count()
    )
}

fun sortLowerCase(s: String):String {
    return s.toLowerCase().toList().sorted().toString();
}

fun part1(pphrases: List<List<String>>) {
    println(pphrases.filter { list -> list.distinct().size == list.size }.count())
}
