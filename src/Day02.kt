class Position(var horizontal: Int = 0, var depth : Int = 0, var aim : Int = 0){
    fun addH(h: Int){ horizontal += h }

    fun addD(d: Int){ depth += d }

    fun addA(a: Int){ aim += a }
}

fun main() {

    fun part1(input: List<String>): Int {
        val pos = Position()
        input.forEach {
            val dirAndVal = it.split(" ")
            when(dirAndVal[0]){
                "forward" -> pos.addH(dirAndVal[1].toInt())
                "down" -> pos.addD(dirAndVal[1].toInt())
                "up" -> pos.addD(-dirAndVal[1].toInt())
            }
        }
        return pos.horizontal * pos.depth
    }

    fun part2(input: List<String>): Int {
        val pos = Position()
        input.forEach {
            val dirAndVal = it.split(" ")
            when(dirAndVal[0]){
                "forward" -> {pos.addH(dirAndVal[1].toInt()); pos.addD(pos.aim * dirAndVal[1].toInt()) }
                "down" -> pos.addA(dirAndVal[1].toInt())
                "up" -> pos.addA(-dirAndVal[1].toInt())
            }
        }
        return pos.horizontal * pos.depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}