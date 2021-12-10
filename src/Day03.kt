fun main() {
    fun part1(input: List<String>): Int {

        val res = List(input[0].length) { 0 }.toMutableList()
        val half = input.size / 2

        input.forEach {
            it.toCharArray().forEachIndexed { index, c -> if (c == "1".single()) {res[index] = res[index] + 1} }
        }

        val gammaRateB = res.joinToString("") { if (it > half) "1" else "0" }
        val epsilonRateB = gammaRateB.map { if( it == "1".single()) "0" else "1"  }.joinToString("")

        val gammaRate = Integer.parseInt(gammaRateB, 2)
        val epsilonRate = Integer.parseInt(epsilonRateB, 2)

        return gammaRate * epsilonRate
    }

    fun findMostCommonValue(input: List<String>, bit : Int): Int{
        val half = input.size / 2.0
        val res = input.map { it[bit] }.fold(0) { acc, char -> if (char == "1".single()) {acc +1 } else acc  }

        return if (res >= half) 1 else 0
    }

    fun findLeastCommonValue(input: List<String>, bit : Int): Int{
        val half = input.size / 2.0
        val res = input.map { it[bit] }.fold(0) { acc, char -> if (char == "0".single()) {acc +1} else acc  }

        return if (res <= half) 0 else 1
    }

    fun findOxygenGeneratorRating(input: List<String>): Int {
        val size = input[0].length
        var newInput = input

        for(i in 0 until size){
            val com = findMostCommonValue(newInput, i)
            newInput = newInput.filter { it[i] == com.toString().single() }
            if(newInput.size == 1) return Integer.parseInt(newInput[0], 2)
        }
        return 0
    }

    fun findCO2ScrubberRating(input: List<String>): Int {
        val size = input[0].length
        var newInput = input

        for(i in 0 until size){
            val com = findLeastCommonValue(newInput, i)
            newInput = newInput.filter { it[i] == com.toString().single() }
            if(newInput.size == 1) return Integer.parseInt(newInput[0], 2)
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        return findOxygenGeneratorRating(input) * findCO2ScrubberRating(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}