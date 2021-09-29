package minesweeper

import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.system.exitProcess

data class Items(
    val XXX: Char = 'X',
    val cell: Char = '.',
    val free: String = "free",
    val mine: String = "mine",
    val star: Char = '*',
    val freeSpace: Char = '/'
)

data class Cor(
    val x: Int,
    val y: Int
)

lateinit var mainField: MutableList<MutableList<Char>>
lateinit var secondaryField: MutableList<MutableList<Char>>
val chars = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
var firstStep = true
var mines by Delegates.notNull<Int>()
val minesMap: MutableList<Cor> = mutableListOf()
val markedSpots: MutableList<Cor> = mutableListOf()
val totalCells = 90
var unCoveredCells = 0

fun main() {
    val items = Items()
    prepareField(items)
}

fun prepareField(items: Items) {
    mainField = MutableList(9) { MutableList(12) { items.cell } }
    secondaryField = MutableList(9) { MutableList(12) { items.freeSpace } }
    for (i in 0..8) {
        mainField[i][0] = '0' + i + 1
        mainField[i][1] = '|'
        mainField[i][11] = '|'
        secondaryField[i][0] = '0' + i + 1
        secondaryField[i][1] = '|'
        secondaryField[i][11] = '|'
    }
    getMines(items)
}

fun getMines(items: Items) {
    println("How many mines do you want on the field?")
    mines = readLine()!!.toInt()
    addMines(items)
}

fun addMines(items: Items, copy: Boolean = false) {
    var bombs = mines
    while (bombs > 0) {
        val row = Random.nextInt(0, 8)
        val column = Random.nextInt(2, 11)
        if (secondaryField[row][column] != items.XXX) secondaryField[row][column] = items.XXX
        bombs--
        minesMap.add(Cor(column - 1, row + 1))
    }
    addHints(items, copy)
}

fun addHints(items: Items, copy: Boolean) {
    for (row in 0..8) {
        for (column in 2..10) {
           if (secondaryField[row][column] != items.XXX) {
               if (column + 1 < 11 && secondaryField[row][column + 1] == items.XXX) addNumber(
                   secondaryField,
                   row,
                   column,
                   items
               )
               if (column + 1 < 11 && row + 1 < 9 && secondaryField[row + 1][column + 1] == items.XXX) addNumber(
                   secondaryField,
                   row,
                   column,
                   items
               )
               if (row + 1 < 9 && secondaryField[row + 1][column] == items.XXX) addNumber(
                   secondaryField,
                   row,
                   column,
                   items
               )
               if (column - 1 > 1 && row + 1 < 9 && secondaryField[row + 1][column - 1] == items.XXX) addNumber(
                   secondaryField,
                   row,
                   column,
                   items
               )
               if (column - 1 > 1 && secondaryField[row][column - 1] == items.XXX) addNumber(
                   secondaryField,
                   row,
                   column,
                   items
               )
               if (column - 1 > 1 && row - 1 >= 0 && secondaryField[row - 1][column - 1] == items.XXX) addNumber(
                   secondaryField,
                   row,
                   column,
                   items
               )
               if (row - 1 >= 0 && secondaryField[row - 1][column] == items.XXX) addNumber(
                   secondaryField,
                   row,
                   column,
                   items
               )
               if (column + 1 < 11 && row - 1 >= 0 && secondaryField[row - 1][column + 1] == items.XXX) addNumber(
                   secondaryField,
                   row,
                   column,
                   items
               )
           }
        }
    }
    if (copy) copyFields(items) else sapper(items)
}

fun addNumber(secondaryField: MutableList<MutableList<Char>>, row: Int, column: Int, items: Items) {
    if (secondaryField[row][column] != items.XXX) {
        if (secondaryField[row][column] == items.freeSpace) secondaryField[row][column] =
            '0' + 1 else secondaryField[row][column] = secondaryField[row][column].plus(1)
    }
}


fun copyFields(items: Items) {
    for (row in 0..8) {
        for (column in 2..11) {
            if (!chars.contains(mainField[row][column])) {
                if (secondaryField[row][column] == items.XXX && mainField[row][column] == items.star) {
                    mainField[row][column] = items.star
                } else if (secondaryField[row][column] == items.XXX ) { //|| chars.contains(secondaryField[row][column])
                    mainField[row][column] = items.cell
                } else {
                    mainField[row][column] = secondaryField[row][column]
                    if (mainField[row][column] == items.star) markedSpots.remove(Cor(column - 1,row + 1))
                    unCoveredCells++
                }
            }
        }
    }
    sapper(items)
}


fun sapper(items: Items) {
    printField(mainField)
    print("Set/unset mine marks or claim a cell as free:")
    val (a, b, c) = readLine()!!.split(" ")
    val row = b.toInt() - 1
    val column = a.toInt() + 1
    when (c) {
        items.mine -> markField(row, column, items)
        items.free -> exploreCell(row, column, items)
        else -> println("Unknown Command")
    }
}

fun markField(row: Int, column: Int, items: Items) {
    firstStep = false
    if (mainField[row][column] != items.star) {
        mainField[row][column] = items.star
        markedSpots.add(Cor(column - 1, row + 1))
    } else {
        if (secondaryField[row][column] == items.XXX) {
            mainField[row][column] = items.cell
            markedSpots.remove(Cor(column - 1, row + 1))
        } else {
            mainField[row][column] = items.cell
            markedSpots.remove(Cor(column - 1, row + 1))
        }
    }
    checkResult(items)
}

fun checkResult(items: Items) {
    if (minesMap.sortedBy { it.x } == markedSpots.sortedBy { it.x }) {
        printField(mainField)
        println("Congratulations! You found all the mines!")
        exitProcess(0)
    } else if (unCoveredCells == totalCells - mines) {
        println("Congratulations! You found all the mines!")
        exitProcess(0)
    } else {
        sapper(items)
    }
}

fun exploreCell(row: Int, column: Int, items: Items) {
    if (secondaryField[row][column] == items.XXX) {
        if (firstStep) {
            firstStep = false
            resetField(items)
        } else {
            mainField[row][column] = items.XXX
            printField(mainField)
            println("You stepped on a mine and failed!")
            exitProcess(0)
        }
    } else if (chars.contains(secondaryField[row][column])) {
        firstStep = false
        mainField[row][column] = secondaryField[row][column]
        unCoveredCells++
        checkResult(items)
    } else {
        firstStep = false
        copyFields(items)
    }
}

fun resetField(items: Items) {
    for (row in 0..8) {
        for (column in 2..10) {
            secondaryField[row][column] = items.freeSpace
        }
    }
    minesMap.clear()
    addMines(items, true)
}


fun printField(mainField: MutableList<MutableList<Char>>) {
    println(" |123456789|")
    println("-|---------|")
    for (line in mainField) {
        println(line.joinToString(""))
    }
    println("-|---------|")
}