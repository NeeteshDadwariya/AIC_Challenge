package com.springboard.aicchallange;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.script.ScriptException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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
        assertEquals(
                "COM B rit2011020 Samantha\n" +
                        "DSA A rit2011001 Julia\n" +
                        "DSA A rit2011020 Samantha", outContent.toString().trim());
        assertEquals("", errContent.toString().trim());
    }
}
