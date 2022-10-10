package de.sdomma.distancecalculator

import de.sdomma.distancecalculator.models.DistanceResult
import de.sdomma.distancecalculator.models.Point
import kotlin.math.pow
import kotlin.math.sqrt

class DistanceCalculator(input: List<Point>) {

    private val result: List<DistanceResult> by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        input.mapIndexed { index, point ->
            input.subList(index + 1, input.size)
                .map { DistanceResult(point, it, calcDistanceBetweenPoints(point, it)) }
        }.flatten()
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

fun List<Point>.calcDistances() : DistanceCalculator = DistanceCalculator(this)