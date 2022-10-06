package de.sdomma.distancecalculator.models

data class DistanceResult(
    val firstPoint: Point,
    val secondPoint: Point,
    val distanceBetweenPoints: Double
)
