package minesweeper

import kotlin.random.Random
import kotlin.system.exitProcess

data class Items(val mine: Char = 'X', val cell: Char = '.')

val minesMap: MutableMap<Int, Int> = mutableMapOf()
val markedSpotsMap: MutableMap<Int, Int> = mutableMapOf()
val numberedSpotsMap = mutableMapOf<Int, Int>()
val chars = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')

fun main() {
    getMines()
}

private fun getMines() {
    println("How many mines do you want on the field?")
    val mines = readLine()!!.toInt()
    createMinesField(mines)
}

private fun createMinesField(mines: Int) {
    val items = Items()
    val minesField = MutableList(12) { MutableList(12) { items.cell } }
    minesField[0] = mutableListOf(' ', '|', '1', '2', '3', '4', '5', '6', '7', '8', '9', '|')
    minesField[1] = mutableListOf('-', '|', '-', '-', '-', '-', '-', '-', '-', '-', '-', '|')
    minesField[11] = mutableListOf('-', '|', '-', '-', '-', '-', '-', '-', '-', '-', '-', '|')
    var num = 1
    for (i in 2..11) {
        if (i < 11) minesField[i][0] = '0' + num else minesField[i][0] = '-'
        num++
        minesField[i][1] = '|'
        minesField[i][11] = '|'
    }
    var minesNumber = mines

    while (minesNumber > 0) {
        val x = Random.nextInt(2, 11)
        val y = Random.nextInt(2, 11)
        minesMap += (y - 1 to x - 1)
        if (minesField[x][y] != items.mine) {
            minesField[x][y] = items.mine
            minesNumber--
        }
    }
    createHints(minesField)
}

fun createHints(minesField: MutableList<MutableList<Char>>) {
    val item = Items()
    for (row in 2..10) {
        for (column in 2..10) {

            if (row == 2 && column == 2) upperLeftCorner(minesField, row, column, item)
            if (row == 2 && column in 3..9) upperLine(minesField, row, column, item)
            if (row == 2 && column == 10) upperRightCorner(minesField, row, column, item)
            if (row in 3..9 && column == 2) leftLine(minesField, row, column, item)
            if (row in 3..9 && column in 3..9) middle(minesField, row, column, item)
            if (row in 3..9 && column == 10) rightLine(minesField, row, column, item)
            if (row == 10 && column == 2) leftLowerCorner(minesField, row, column, item)
            if (row == 10 && column in 3..9) lowerLine(minesField, row, column, item)
            if (row == 10 && column == 10) lowerRightCorner(minesField, row, column, item)
        }
    }
    prepareField(minesField)
}

fun prepareField(minesField: MutableList<MutableList<Char>>) {
    val items = Items()
    for(x in 2..10) {
        for (y in 2..10) {
            if (minesField[x][y] == items.mine) {
                minesField[x][y] = items.cell
            } else if (chars.contains(minesField[x][y])) {
                numberedSpotsMap += (y - 1 to x - 1)
                //minesField[x][y] = items.cell
            }
        }
    }
    /**
     * There is an issue here...
     * the for loops goes onto all the cells correctly, and the conditions are set well.
     * also, the debug shows that it goes to store the value. but not all the values are in both
     * [minesMap] and [numberedSpotsMap], despite [minesField] is prepared as needed.
     */
    println(minesMap)
    println(numberedSpotsMap)
    printField(minesField)
}

fun lowerRightCorner(minesField: MutableList<MutableList<Char>>, row: Int, column: Int, item: Items) {
    if (minesField[row][column] == item.mine) return
    var mines = 0


    if (minesField[row][column - 1] == item.mine) mines++
    if (minesField[row - 1][column - 1] == item.mine) mines++
    if (minesField[row - 1][column] == item.mine) mines++

    if (mines > 0) minesField[row][column] = '0' + mines

}

fun lowerLine(minesField: MutableList<MutableList<Char>>, row: Int, column: Int, item: Items) {
    if (minesField[row][column] == item.mine) return
    var mines = 0

    if (minesField[row][column + 1] == item.mine) mines++
    if (minesField[row - 1][column + 1] == item.mine) mines++
    if (minesField[row - 1][column] == item.mine) mines++
    if (minesField[row - 1][column - 1] == item.mine) mines++
    if (minesField[row][column - 1] == item.mine) mines++

    if (mines > 0) minesField[row][column] = '0' + mines

}

fun leftLowerCorner(minesField: MutableList<MutableList<Char>>, row: Int, column: Int, item: Items) {
    if (minesField[row][column] == item.mine) return
    var mines = 0

    if (minesField[row][column + 1] == item.mine) mines++
    if (minesField[row - 1][column + 1] == item.mine) mines++
    if (minesField[row - 1][column] == item.mine) mines++

    if (mines > 0) minesField[row][column] = '0' + mines
}

fun rightLine(minesField: MutableList<MutableList<Char>>, row: Int, column: Int, item: Items) {
    if (minesField[row][column] == item.mine) return
    var mines = 0

    if (minesField[row - 1][column] == item.mine) mines++
    if (minesField[row - 1][column - 1] == item.mine) mines++
    if (minesField[row][column - 1] == item.mine) mines++
    if (minesField[row + 1][column - 1] == item.mine) mines++
    if (minesField[row + 1][column] == item.mine) mines++

    if (mines > 0) minesField[row][column] = '0' + mines

}

fun middle(minesField: MutableList<MutableList<Char>>, row: Int, column: Int, item: Items) {
    if (minesField[row][column] == item.mine) return
    var mines = 0

    if (minesField[row - 1][column] == item.mine) mines++
    if (minesField[row - 1][column + 1] == item.mine) mines++
    if (minesField[row][column + 1] == item.mine) mines++
    if (minesField[row + 1][column + 1] == item.mine) mines++
    if (minesField[row + 1][column] == item.mine) mines++
    if (minesField[row + 1][column - 1] == item.mine) mines++
    if (minesField[row][column - 1] == item.mine) mines++
    if (minesField[row - 1][column - 1] == item.mine) mines++

    if (mines > 0) minesField[row][column] = '0' + mines
}

fun leftLine(minesField: MutableList<MutableList<Char>>, row: Int, column: Int, item: Items) {
    if (minesField[row][column] == item.mine) return
    var mines = 0

    if (minesField[row - 1][column] == item.mine) mines++
    if (minesField[row - 1][column + 1] == item.mine) mines++
    if (minesField[row][column + 1] == item.mine) mines++
    if (minesField[row + 1][column + 1] == item.mine) mines++
    if (minesField[row + 1][column] == item.mine) mines++

    if (mines > 0) minesField[row][column] = '0' + mines

}

fun upperRightCorner(minesField: MutableList<MutableList<Char>>, row: Int, column: Int, item: Items) {
    if (minesField[row][column] == item.mine) return
    var mines = 0

    if (minesField[row][column - 1] == item.mine) mines++
    if (minesField[row + 1][column - 1] == item.mine) mines++
    if (minesField[row + 1][column] == item.mine) mines++

    if (mines > 0) minesField[row][column] = '0' + mines
}

private fun upperLeftCorner(minesField: MutableList<MutableList<Char>>, row: Int, column: Int, item: Items) {
    if (minesField[row][column] == item.mine) return
    var mines = 0

    if (minesField[row][column + 1] == item.mine) mines++
    if (minesField[row + 1][column + 1] == item.mine) mines++
    if (minesField[row + 1][column] == item.mine) mines++

    if (mines > 0) minesField[row][column] = '0' + mines
}

private fun upperLine(minesField: MutableList<MutableList<Char>>, row: Int, column: Int, item: Items) {
    if (minesField[row][column] == item.mine) return
    var mines = 0

    if (minesField[row][column + 1] == item.mine) mines++
    if (minesField[row + 1][column + 1] == item.mine) mines++
    if (minesField[row + 1][column] == item.mine) mines++
    if (minesField[row + 1][column - 1] == item.mine) mines++
    if (minesField[row][column - 1] == item.mine) mines++

    if (mines > 0) minesField[row][column] = '0' + mines

}

private fun printField(list: MutableList<MutableList<Char>>) {
    for (line in list) {
        println(line.joinToString(""))
    }
    sapper(list)
}

fun sapper(minesField: MutableList<MutableList<Char>>) {
    print("Set/delete mine marks (x and y coordinates):")
    val (x, y) = readLine()!!.split(" ").map { it.toInt() }
    if (minesMap.containsKey(y)) checkMine(minesField, y, x) else gotNumberOrEmptySpace(minesField, y, x)
}

fun gotNumberOrEmptySpace(minesField: MutableList<MutableList<Char>>, x: Int, y: Int) {
    when {
        chars.contains(minesField[x + 1][y + 1]) -> {
            println("There is a number here!")
            sapper(minesField)
        }
        else -> checkCellAndResult(minesField, x, y)
    }
}

fun checkMine(minesField: MutableList<MutableList<Char>>, x: Int, y: Int) {
    when {
        (minesMap.getValue(x) == y) -> checkCellAndResult(minesField, x, y)
        chars.contains(minesField[x + 1][y + 1]) -> {
            println("There is a number here!")
            sapper(minesField)
        }
        else -> checkCellAndResult(minesField, x, y)
    }
}

fun checkCellAndResult(minesField: MutableList<MutableList<Char>>, x: Int, y: Int) {
    if (minesField[x + 1][y + 1] == '.') {
        minesField[x + 1][y + 1] = '*'
        markedSpotsMap += (y to x)
        checkResult(minesField)
    } else {
        minesField[x + 1][y + 1] = '.'
        markedSpotsMap.remove(y)
        checkResult(minesField)
    }
}

fun checkResult(minesField: MutableList<MutableList<Char>>) {
    val boolean = minesMap.keys.toList().sorted() == markedSpotsMap.keys.toList().sorted() &&
            minesMap.values.toList().sorted() == markedSpotsMap.values.toList().sorted()
    when {
        boolean -> {
            println("Congratulations! You found all the mines!")
            exitProcess(0)
        }
        else -> {
            printField(minesField)
            sapper(minesField)
        }
    }
}