package algorithms

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Method under test [bullsAndCowsGetHint]
 */
internal class BullsAndCowsTest {

    @Test
    fun bullsAndCowsGetHintTest() {
        assertEquals("1A3B", bullsAndCowsGetHint("1807", "7810"))
        assertEquals("1A1B", bullsAndCowsGetHint("1123", "0111"))
    }
}