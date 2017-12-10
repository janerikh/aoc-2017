import java.io.File

fun main(args: Array<String>) {

    val input = File(ClassLoader.getSystemResource("input.txt").file)
            .readLines()

    process(input.toMutableList())
}

fun process(input: MutableList<String>) {

    val cleanLine = input.map { s -> cleanLine(s) }
    println("part1: " + scoreGroups(cleanLine))
}

fun scoreGroups(input: List<String>): Int {
    var totalScore = 0
    var depth: Int
    for (line in input) {
        var lineScore = 0
        depth = 0
        for (c in line) {
            if (c.equals('{')) {
                lineScore += 1 + depth
                totalScore += 1 + depth
                depth++
            } else if (c.equals('}')) {
                depth--
            }
        }
        println("line: $line has score $lineScore")
    }
    return totalScore
}

fun cleanLine(line: String): String {
    // clean away !x rule
    var newL = cleanExclamation(line)
    newL = cleanGarbage(newL)
    return newL
}

fun cleanGarbage(line: String): String {
    println("line2: $line")
    var newL = ""
    var i = 0
    var garbage = false
    var gCount = 0
    while (i < line.length) {
        if (!garbage && line[i].equals('<')) {
            garbage = true
        } else if (garbage && line[i].equals('>')){
            garbage = false
        } else if (!garbage){
            newL = newL + line[i]
        } else {
            gCount++
        }
        i++
    }
    println("newL2: $newL")
    println("garbage Count: $gCount")
    println()
    return newL
}

private fun cleanExclamation(line: String): String {
    println("line: $line")
    var newL = ""
    var i = 0
    while (i < line.length) {
        if (line[i].equals('!')) {
            //skip next char
            i++
        } else {
            newL = newL + line[i]
        }
        i++
    }
    println("newL: $newL")
    println()
    return newL
}
