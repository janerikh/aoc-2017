import kotlin.math.abs

fun main(args: Array<String>) {

//    part1()
    part2()
}

fun part1() {

    val input = 368078

    // find size of spiral for input
    var size = 0
    var rowSize = 1
    while (size < input) {
        rowSize += 2
        size = rowSize * rowSize
    }

    // init 2d array of 0's, with size of spiral
    var spiral: Array<Array<Int>> =
            Array(rowSize, {
                Array(rowSize, {
                    0
                })
            })

    // find the middle of the spiral
    val middle = (rowSize / 2)

    // fill spiral with correct values
    val rowIndexMax = rowSize-1
    var i = rowIndexMax
    var j = rowIndexMax
    var spiralValue = size
    var currentDirection = Direction.LEFT
    while (true) {
        // set value
        spiral[i][j] = spiralValue--

        //Done condition
        if (i == middle && j == middle) {
            break;
        }

        // move
        var changeDirection = false
        when (currentDirection) {
            Direction.RIGHT -> {
                if (++j > rowIndexMax || isSpiralFieldSet(spiral, i, j)) {
                    j--
                    changeDirection = true
                }
            }
            Direction.UP -> {
                if (--i < 0 || isSpiralFieldSet(spiral, i, j)) {
                    i++
                    changeDirection = true
                }
            }
            Direction.LEFT -> {
                if (--j < 0 || isSpiralFieldSet(spiral, i, j)) {
                    j++
                    changeDirection = true
                }
            }
            Direction.DOWN -> {
                if (++i > rowIndexMax || isSpiralFieldSet(spiral, i, j)) {
                    i--
                    changeDirection = true
                }
            }
        }

        // find correct direction
        if (changeDirection) {
            currentDirection = currentDirection.prev()
            when (currentDirection) {
                Direction.RIGHT -> j++
                Direction.UP -> i--
                Direction.LEFT -> j--
                Direction.DOWN -> i++
            }
        }
    }

    findManhattenDistance(spiral, input, rowSize, middle)

}

fun findManhattenDistance(spiral: Array<Array<Int>>, input: Int, rowSize: Int, middle: Int) {
    var si = 0
    var sj = 0

    // find coordinates of starting point
    for (i in rowSize -1 downTo 0) {
        for (j in rowSize -1 downTo 0) {
            if (input == spiral[i][j]) {
                si = i
                sj = j
            }
        }
    }

    var sum = abs(si-middle) + abs(sj-middle)
    println(sum)
}

private fun isSpiralFieldSet(spiral: Array<Array<Int>>, i: Int, j: Int) =
        spiral[i][j] > 0

fun printArray(array: Array<Array<Int>>, size: Int) {
    for (i in 0..size-1) {
        for (j in 0..size-1) {
            print("" + array[i][j] + " ")
        }
        println()
    }
}

fun part2() {

    val input = 368078

    // gave up trying to find the right size for the array. Just made it big enough
    var rowSize = 1000

    var spiral: Array<Array<Int>> =
            Array(rowSize, {
                Array(rowSize, {
                    0
                })
            })
    // find the middle of the spiral
    val middle = (rowSize / 2)


    var i = middle
    var j = middle
    var currentDirection = Direction.RIGHT
    spiral[i][j] = 1
    while (true) {
        var sum = 0
        // sum row below
        if (i-1 >= 0) {
            if (j-1 >= 0) {
                sum += spiral[i-1][j-1]
            }
            if (j+1 < rowSize) {
                sum += spiral[i-1][j+1]
            }
            sum += spiral[i-1][j]

        }
        // sum same row, except self
        if (j-1 >= 0) {
            sum += spiral[i][j-1]
        }
        if (j+1 < rowSize) {
            sum += spiral[i][j+1]
        }
        // sum row under
        if (i+1 < rowSize) {
            if (j-1 >= 0) {
                sum += spiral[i+1][j-1]
            }
            if (j+1 < rowSize) {
                sum += spiral[i+1][j+1]
            }
            sum += spiral[i+1][j]
        }


        // special case for first iteration
        if (i==middle && j==middle) {
            sum = 1
        }

        // set square value
        spiral[i][j] = sum


        // find next square, and change direction if needed
        when (currentDirection) {
            Direction.RIGHT -> {
                j++
                // are we at the end of the array or the row above us is not set
                if (j >= rowSize) {
                    j--
                    currentDirection = currentDirection.next()
                } else if (spiral[i - 1][j] == 0) {
                    currentDirection = currentDirection.next()
                }
            }
            Direction.UP -> {
                i--
                // are we at the end of the array or the column to our left is not set
                if (i < 0) {
                    i++
                    currentDirection = currentDirection.next()
                } else if (spiral[i][j - 1] == 0) {
                    currentDirection = currentDirection.next()
                }
            }
            Direction.LEFT -> {
                j--
                // are we at the end of the array or the row below us is not set
                if (j < 0) {
                    j++
                    currentDirection = currentDirection.next()
                } else if (spiral[i + 1][j] == 0) {
                    currentDirection = currentDirection.next()
                }
            }
            Direction.DOWN -> {
                i++
                // are we at the end of the array or the column to our right is not set
                if (i >= rowSize) {
                    i--
                    currentDirection = currentDirection.next()
                } else if (spiral[i][j + 1] == 0) {
                    currentDirection = currentDirection.next()
                }
            }
        }

        // break condtiotion
        if (sum  > input) {
            println("sum $sum")
            break;
        }
    }

}
