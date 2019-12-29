package com.springboard.aicchallange;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ValidateEmailTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final ValidateEmail validateEmail = new ValidateEmail();

    @Before
    public void setUpStreams() {
        MockitoAnnotations.initMocks(this);
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
        validateEmail.printResult("neeteshdadwariya@gmail.com");
        assertEquals("Valid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test2() {
        validateEmail.printResult("1neeteshdadwariya@gmail.com");
        assertEquals("Invalid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test3() {
        validateEmail.printResult("neetesh.dadwariya@gmail.com");
        assertEquals("Invalid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test4() {
        validateEmail.printResult("neetesh%dadwariya@gmail.com");
        assertEquals("Invalid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test5() {
        validateEmail.printResult("neeteshdadwariya@gmail.comm");
        assertEquals("Invalid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test6() {
        validateEmail.printResult("neeteshdadwariya@gmail.co.in");
        assertEquals("Valid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test7() {
        validateEmail.printResult("neeteshdadwariya@gmail.com.in");
        assertEquals("Valid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test8() {
        validateEmail.printResult("neetesh511dadwariya@gmail.com");
        assertEquals("Valid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test9() {
        validateEmail.printResult("neeteshdadwariya@gmail2345.com");
        assertEquals("Valid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test10() {
        validateEmail.printResult("neeteshdadwariya@gmail.com1234");
        assertEquals("Invalid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test11() {
        validateEmail.printResult("");
        assertEquals("Invalid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test12() {
        validateEmail.printResult("neetesh");
        assertEquals("Invalid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test13() {
        validateEmail.printResult("@gmail.com");
        assertEquals("Invalid", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }
}
