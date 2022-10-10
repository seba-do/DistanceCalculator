package de.sdomma.distancecalculator

import de.sdomma.distancecalculator.models.Point
import org.junit.Test
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.test.assertEquals

class DistanceCalculatorTest {

    // possible combinations:
    // (1,4) x (4,4) = 3
    // (1,4) x (3,2) = 2.828427
    // (1,4) x (5,1) = 5
    // (4,4) x (3,2) = 2.2360681
    // (4,4) x (5,1) = 3.1622784
    // (3,2) x (5,1) = 2.2360681
    private val testData = listOf(
        Point(1, 4),
        Point(4, 4),
        Point(3, 2),
        Point(5, 1)
    )

    private val testDistanceFormat =
        DecimalFormat("#.###").also { it.roundingMode = RoundingMode.FLOOR }

    @Test
    fun testClosestDistanceResult() {
        val result = testData.calcDistances().getClosestDistanceResult()

        assertEquals(2, result.size)
        assertEquals(testData[1], result.first().firstPoint)
        assertEquals(testData[2], result.first().secondPoint)
        assertEquals("2,236", testDistanceFormat.format(result.first().distanceBetweenPoints))

        assertEquals(testData[2], result.last().firstPoint)
        assertEquals(testData[3], result.last().secondPoint)
        assertEquals("2,236", testDistanceFormat.format(result.last().distanceBetweenPoints))
    }

    @Test
    fun testFarthermostDistanceResult() {
        val result = testData.calcDistances().getFarthermostDistanceResult()

        assertEquals(1, result.size)
        assertEquals(testData[0], result.first().firstPoint)
        assertEquals(testData[3], result.first().secondPoint)
        assertEquals("5", testDistanceFormat.format(result.first().distanceBetweenPoints))
    }

    @Test
    fun testEmptyInput() {
        val closestResult = emptyList<Point>().calcDistances().getClosestDistanceResult()
        assertEquals(null, closestResult.firstOrNull())

        val farthermostResult = emptyList<Point>().calcDistances().getFarthermostDistanceResult()
        assertEquals(null, farthermostResult.firstOrNull())
    }
}