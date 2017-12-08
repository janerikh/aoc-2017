import java.io.File

fun main(args: Array<String>) {

    val input = File(ClassLoader.getSystemResource("input.txt").file)
            .readLines()
            .map { s -> s.split(" ") }

    solve(input)
}

fun solve(input: List<List<String>>) {

    val varValueMap = mutableMapOf<String, Int>()
    val maxEverVal = parseInstructions(input, varValueMap)

    println("part1: " + varValueMap.values.max())
    println("part2: " + maxEverVal)
}

fun parseInstructions(instructions: List<List<String>>, varValueMap: MutableMap<String, Int>): Int {

    var maxEverVal = 0
    for (inst in instructions) {
        val register = inst[0]
        val value = inst[2].toInt()
        val operator = inst[1]
        // inst[3] is if always
        val exprp1 = inst[4]
        val comp = inst[5]
        val exprp2 = inst[6]

        var exprval = lookupReg(exprp1, varValueMap)
        val compa = Compa.valueOfStr(comp)
        if (compa.compare(exprval, exprp2.toInt())) {
            val regVal = lookupReg(register, varValueMap)
            val newVal = Operator.valueOfStr(operator).invoke(regVal, value)
            varValueMap[register] = newVal
            if (newVal > maxEverVal) {
                maxEverVal = newVal
            }
        }

    }
    return maxEverVal
}

fun lookupReg(register: String, varValueMap: MutableMap<String, Int>): Int {
    val value = varValueMap.get(register)
    if (value != null) {
        return value
    } else {
        varValueMap[register] = 0
        return 0
    }
}

