package com.springboard.aicchallange;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.script.ScriptException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class StudentReportTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final StudentReport studentReport = new StudentReport();

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
    public void test1() throws ScriptException {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("student-report-test-inputdata-1.json");
        String jsonString = StudentReport.getJSONFromSInputStream(in);
        studentReport.processReport(jsonString);
        String expectedResult = readStringFromFileName("student-report-test-expectedresult-1.txt");
        assertEquals(expectedResult, outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test2() throws ScriptException {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("student-report-test-inputdata-2.json");
        String jsonString = StudentReport.getJSONFromSInputStream(in);
        studentReport.processReport(jsonString);
        String expectedResult = readStringFromFileName("student-report-test-expectedresult-2.txt");
        assertEquals(expectedResult, outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test3() throws ScriptException {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("student-report-test-inputdata-3.json");
        String jsonString = StudentReport.getJSONFromSInputStream(in);
        studentReport.processReport(jsonString);
        String expectedResult = readStringFromFileName("student-report-test-expectedresult-3.txt");
        assertEquals(expectedResult, outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test4() throws ScriptException {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("student-report-test-inputdata-4.json");
        String jsonString = StudentReport.getJSONFromSInputStream(in);
        studentReport.processReport(jsonString);
        String expectedResult = readStringFromFileName("student-report-test-expectedresult-4.txt");
        assertEquals(expectedResult, outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    @Test
    public void test5() throws ScriptException {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("student-report-test-inputdata-5.json");
        String jsonString = StudentReport.getJSONFromSInputStream(in);
        studentReport.processReport(jsonString);
        String expectedResult = readStringFromFileName("student-report-test-expectedresult-5.txt");
        assertEquals(expectedResult, outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }

    private String readStringFromFileName(String fileName) {
        Scanner sc = new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream(fileName));
        String str = "";
        while (sc.hasNextLine()) {
            str += sc.nextLine() + "\n";
        }
        return str.trim();
    }

}
