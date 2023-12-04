fun main() {
    data class Number(val value: Int, var marked: Boolean = false)

    fun hasColumnMarked(board: List<List<Number>>): Boolean {
        for (column in board.indices) {
            if (board.all { it[column].marked }) {
                return true
            }
        }
        return false
    }

    fun hasRowMarked(board: List<List<Number>>): Boolean {
        for (row in board) {
            if (row.all { it.marked }) {
                return true
            }
        }
        return false
    }

    fun count(board: List<List<Number>>): Pair<Int, Int> {
        var countMarked = 0
        var countUnmarked = 0

        for (row in board) {
            for (number in row) {
                if (number.marked) countMarked += number.value else countUnmarked += number.value
            }
        }
        return Pair(countMarked, countUnmarked)
    }
    fun part1(input: List<String>): Int {

        var zeInput = input.toMutableList()
        val values = zeInput[0].split(",").map { it.toInt() }

        zeInput.removeFirst()

        val stringBoards = mutableListOf<List<String>>()

        while(zeInput.isNotEmpty()){
            zeInput.removeFirst()

            stringBoards.add(zeInput.take(5))
            zeInput = zeInput.drop(5).toMutableList()
        }

        val boards = stringBoards
            .map { board ->
                board.map { row ->
                    row.split(" ")
                        .filter { v -> v.isNotEmpty() }
                        .map { v -> Number(v.toInt())}
                }
            }

        for (value in values) {
            for (board in boards) {
                for (row in board) {
                    for (number in row) {
                        if (number.value == value) {
                            number.marked = true
                        }
                    }
                }

                if(hasColumnMarked(board) || hasRowMarked(board)){
                    return count(board).second * value
                }
            }

        }

        return -1
    }

    fun part2(input: List<String>): Int {

        var zeInput = input.toMutableList()
        val values = zeInput[0].split(",").map { it.toInt() }

        zeInput.removeFirst()

        val stringBoards = mutableListOf<List<String>>()

        while(zeInput.isNotEmpty()){
            zeInput.removeFirst()

            stringBoards.add(zeInput.take(5))
            zeInput = zeInput.drop(5).toMutableList()
        }

        val boards = stringBoards
            .map { board ->
                board.map { row ->
                    row.split(" ")
                        .filter { v -> v.isNotEmpty() }
                        .map { v -> Number(v.toInt())}
                }
            }

        val wonBoards = mutableListOf<List<List<Number>>>()

        for (value in values) {
            for (board in boards) {
                if(!wonBoards.contains(board)){
                    for (row in board) {
                        for (number in row) {
                            if (number.value == value) {
                                number.marked = true
                            }
                        }
                    }

                    if(hasColumnMarked(board) || hasRowMarked(board)) {
                        wonBoards.add(board)

                        if (wonBoards.size == boards.size) {
                            return count(board).second * value
                        }
                    }
                }
            }
        }
        return -1
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}