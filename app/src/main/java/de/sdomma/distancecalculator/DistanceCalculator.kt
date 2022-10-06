package de.sdomma.distancecalculator

import de.sdomma.distancecalculator.models.DistanceResult
import de.sdomma.distancecalculator.models.Point
import kotlin.math.pow
import kotlin.math.sqrt

class DistanceCalculator {

    private var result: List<DistanceResult> = emptyList()

    fun calcDistances(input: List<Point>): DistanceCalculator {
        mutableListOf<DistanceResult>()
            .also { output ->
                input.forEachIndexed { index, point ->
                    input.subList(index + 1, input.size)
                        .map { DistanceResult(point, it, calcDistanceBetweenPoints(point, it)) }
                        .let { output.addAll(it) }
                }
            }
            .also { result = it }

        return this
    }


    private fun calcDistanceBetweenPoints(firstPoint: Point, secondPoint: Point) = sqrt(
        (firstPoint.x - secondPoint.x).toDouble().pow(2)
                + (firstPoint.y - secondPoint.y).toDouble().pow(2)
    )

    fun getClosestDistanceResult(): List<DistanceResult> {
        val minDistance = result.minByOrNull { it.distanceBetweenPoints }?.distanceBetweenPoints
        return result.filter { it.distanceBetweenPoints == minDistance }
    }

    fun getFarthermostDistanceResult(): List<DistanceResult> {
        val maxDistance = result.maxByOrNull { it.distanceBetweenPoints }?.distanceBetweenPoints
        return result.filter { it.distanceBetweenPoints == maxDistance }
    }
}

fun List<Point>.calcDistances() : DistanceCalculator = DistanceCalculator().calcDistances(this)