package com.springboard.aicchallange;

import javafx.util.Pair;

import java.util.Scanner;
import java.util.Stack;

public class BalanceParentheses {

    private static final String EMPTY_STRING = "";
    private static final String OPENING_BRACKETS = "({[";
    private static final String CLOSING_BRACKETS = ")}]";

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().trim();

        //Evaluate and print result.
        new BalanceParentheses().printOutput(str);

    }

    /**
     * This function is used to print the output to the STDOUT
     *
     * @param str - The input string
     */
    public void printOutput(String str) {
        Pair<Boolean, String> balancingResult = balanceParentheses(str);
        if (balancingResult.getKey()) {
            System.out.println("Y " + balancingResult.getValue());
        } else {
            System.out.println("N " + balancingResult.getValue());
        }
    }

    /**
     * This function is used to balance the parentheses in the provided string. The return value for the function is a
     * tuple with the first argument as true if the string is balanced, else false, and the second argument will return
     * the string after removing all other characters than parentheses. For example: if the given string is:
     * (4+8)/(5*(4+4)), then since the string has balanced parentheses, the return value would be [true, ()(())].
     *
     * @param str - The input string
     * @return - the return tuple.
     */
    private Pair<Boolean, String> balanceParentheses(String str) {
        int strLen = str.length();
        String returnString = EMPTY_STRING;

        //If the provided string is empty, then return with false.
        if (strLen == 0) {
            return new Pair<>(false, returnString);
        }

        // Stack used to keep track of matching parentheses.
        Stack<Character> stack = new Stack<>();

        // Iterate through all characters of the string.
        for (int i = 0; i < strLen; i++) {
            Character ch = str.charAt(i);

            // If the current char is opening bracket, then push it to stack.
            if (OPENING_BRACKETS.indexOf(ch) > -1) {
                returnString += ch;
                stack.push(ch);
            }
            // If the current char is closing bracket, then check if the last element is the matching opening bracket.
            // If yes, then pop from stack, else push it to stack.
            else if (CLOSING_BRACKETS.indexOf(ch) > -1) {
                returnString += ch;
                if (!stack.isEmpty()) {
                    Character lastElement = stack.lastElement();
                    int closedBracketIndex = CLOSING_BRACKETS.indexOf(ch);
                    if (OPENING_BRACKETS.charAt(closedBracketIndex) == lastElement) {
                        //Popping from stack. This bracket has matching parentheses.
                        stack.pop();
                    } else {
                        stack.push(ch);
                    }
                } else {
                    stack.push(ch);
                }
            }

        }

        //After the code run, if there is no element in the stack, then the string is balanced parentheses, else not.
        if (stack.isEmpty()) {
            return new Pair<>(true, returnString);
        } else {
            return new Pair<>(false, returnString);
        }
    }
}
