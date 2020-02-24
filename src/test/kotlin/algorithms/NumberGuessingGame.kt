package algorithms

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Class under test [NumberGuessingGame].
 */
internal class NumberGuessingGameTest {

    @Test
    fun guessNumberTest() {
        assertEquals(1, NumberGuessingGame(1).guessNumber(1))
        assertEquals(6, NumberGuessingGame(6).guessNumber(10))
        assertEquals(699, NumberGuessingGame(699).guessNumber(1400))
        assertEquals(3, NumberGuessingGame(3).guessNumber(1400))
    }
}