package algorithms

import java.util.*

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 * Note that an empty string is also considered valid.
 */
internal fun validParenthesis(input: String): Boolean {
    if (input.length % 2 != 0) {
        //Violates the constraints
        return false
    }
    val stack = Stack<Char>()
    input.forEach { value ->
        if (isOpeningParenthesis(value)) {
            //Push it onto our stack
            stack.push(value)
        } else {
            //If the stack is empty we shouldn't reach here unless the input is invalid
            if (stack.isEmpty()) {
                return false
            }
            //Pop it off the stack
            if (stack.peek() == getOpeningParenthesis(value)) {
                stack.pop()
            } else {
                return false
            }
        }
    }
    return stack.isEmpty()
}

private fun getOpeningParenthesis(parenthesis: Char) = when (parenthesis) {
    ')' -> '('
    '}' -> '{'
    else -> '['
}

private fun isOpeningParenthesis(parenthesis: Char) = parenthesis == '(' || parenthesis == '{' || parenthesis == '['