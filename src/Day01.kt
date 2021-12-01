fun main() {
    fun part1(input: List<String>): Int {
        val intpairs = input.map { it.toInt() }.zipWithNext()
        return intpairs.fold(0) { acc, zePair -> if(zePair.second > zePair.first)  acc + 1 else acc}
    }

    fun part2(input: List<String>): Int {
        val ints = input.map { it.toInt() }

        val ser1 = ints.dropLast(2)
        val ser2 = ints.drop(1).dropLast(1)
        val ser3  = ints.drop(2)

        val sums = (ser1.indices).map { ser1[it] + ser2[it] + ser3[it]}.zipWithNext()

        return sums.fold(0) { acc, zePair -> if(zePair.second > zePair.first)  acc + 1 else acc}
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}