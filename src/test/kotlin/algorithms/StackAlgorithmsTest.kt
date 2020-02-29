package algorithms

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class StackAlgorithmsTest {

    @Test
    fun validParenthesisTest() {
        assertTrue(validParenthesis("()"))
        assertTrue(validParenthesis("()[]{}"))
        assertTrue(validParenthesis("{[]}"))
        assertFalse(validParenthesis("(]"))
        assertFalse(validParenthesis("([)]"))
        assertFalse(validParenthesis("(("))
        assertFalse(validParenthesis("){"))
    }
}