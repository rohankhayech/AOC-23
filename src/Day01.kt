/**
 * Day 1: Trebuchet?!
 */
fun main() {
    fun part1(input: List<String>): Int {
        // Map each line to its calibration value and take the sum.
        return input.sumOf { line ->
            // Combine the first and last digit to get the calibration value.
            "${line.first { it.isDigit() }}${line.last { it.isDigit()}}"
                .toInt()// Convert to integer.
        }

    }

    fun part2(input: List<String>): Int {

        // Spelled out digits and their conversions.
        // This adds the correct digit to the string but maintains the first and last characters for overlaps.
        val digits = mapOf(
            "one" to "o1e",
            "two" to "t2o",
            "three" to "t3e",
            "four" to "f4r",
            "five" to "f5e",
            "six" to "s6x",
            "seven" to "s7n",
            "eight" to "e8t",
            "nine" to "n9e"
        )

        // Convert all spelled out digits.
        return input.map {
            var line = it
            for (digit in digits) {
                line = line.replace(digit.key, digit.value)
            }
            line
        }.let { part1(it) }  // Use part 1 solver to find calibration value sum.

    }

    val testInput1 = readInput("Day01_test1")
    println("Part 1 correct?: ${part1(testInput1) == 142}")

    val testInput2 = readInput("Day01_test2")
    println("Part 2 correct?: ${part2(testInput2) == 281}")

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
