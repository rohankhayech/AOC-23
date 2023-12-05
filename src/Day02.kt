import kotlin.math.max

/**
 * Day 2: Cube Conundrum
 */
fun main() {
    data class CubeSet(var r: Int = 0, var g: Int = 0, var b: Int = 0)

    data class Game(val id: Int, val sets: List<CubeSet>)

    /**
     * Maps the input line into a game object.
     */
    fun List<String>.mapToGames(): List<Game> {
        return map { // Map the input to a data class.
            val (gameStr, setsStr) = it.split(":") // Split the game and set info
            val id = gameStr.filter { char -> char.isDigit() }.toInt()
            val sets = setsStr.split(";") // Split up into sets.
                .map { setStr ->
                    val set = CubeSet()
                    setStr.split(",").forEach { colorCount -> // Split up into colors.
                        val (numStr, color) = colorCount.split(" ").drop(1) // Split up into count and color.
                        val num = numStr.toInt()
                        when (color) { // Set the amount for the color.
                            "red" -> set.r = num
                            "green" -> set.g = num
                            "blue" -> set.b = num
                        }
                    }
                    set
                }
            Game(id, sets)
        }
    }

    fun part1(input: List<String>): Int {
        return input.mapToGames()
            .filterNot {// Filter out any impossible games
                it.sets.any { set -> // where any set
                    (set.r > 12 || set.g > 13 || set.b > 14) // has more cubes of any color than in the bag.
                }
            }.sumOf { it.id } // Sum the IDs of possible games.
    }

    fun part2(input: List<String>): Int {
        return input.mapToGames()
            .map {
                // Find the minimum set of cubes that makes the game possible.
                it.sets.reduce { min, set ->
                    // If the number of cubes in the set is more than the current minimum, set it to this set's amount.
                    CubeSet(
                        r = max(min.r, set.r),
                        g = max(min.g, set.g),
                        b = max(min.b, set.b)
                    )
                }
            }.sumOf { it.r * it.g * it.b }
    }

    val testInput = readInput("Day02_test")
    println("Part 1 correct?: ${part1(testInput) == 8}")
    println("Part 2 correct?: ${part2(testInput) == 2286}")

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
