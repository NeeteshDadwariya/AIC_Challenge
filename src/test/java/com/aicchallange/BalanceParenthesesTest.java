package com.aicchallange;

import aicchallange.BalanceParentheses;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BalanceParenthesesTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final BalanceParentheses balanceParentheses = new BalanceParentheses();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void test1() {
        balanceParentheses.printOutput("(4+8)/(5*(4+4))");
        assertEquals("Y ()(())", outContent.toString());
        assertEquals("", errContent.toString());
    }
}
