package com.springboard.aicchallange;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentReport {

    public static final String EMPTY_STRING = "";

    public static void main(String[] args) throws ScriptException {
        String jsonString = getJSONFromSInputStream(System.in);
        new StudentReport().processReport(jsonString);
    }

    public void processReport(String jsonString) throws ScriptException {
        List<Map<String, Object>> students = parseJSONToMap(jsonString);
        List<Report> reportData = createReportDtoFromMap(students);
        sortReportData(reportData);
        printReport(reportData);
    }

    private void printReport(List<Report> reportData) {
        reportData.forEach(report -> {
            System.out.println(report.getCode() + " " + report.getGrade() + " " + report.getEnrollment() + " " + report.getName());
        });
    }

    private void sortReportData(List<Report> reportData) {
        reportData.sort((o1, o2) -> {
            if (o1.getCode().equals(o2.getCode())) {
                if (o1.getGrade().equals(o2.getGrade())) {
                    if (o1.getEnrollment().equals(o2.getEnrollment())) {
                        return o1.getName().compareTo(o2.getName());
                    } else {
                        return o1.getEnrollment().compareTo(o2.getEnrollment());
                    }
                } else {
                    return o1.getGrade().compareTo(o2.getGrade());
                }
            } else {
                return o1.getCode().compareTo(o2.getCode());
            }
        });
    }

    private List<Report> createReportDtoFromMap(List<Map<String, Object>> students) {
        List<Report> reportData = new ArrayList<>();
        students.forEach(s -> {
            List<Map<String, Object>> subjects = (List<Map<String, Object>>) s.get("subject");
            subjects.forEach(sub -> {
                Report report = new Report();
                report.setEnrollment((String) s.get("enrollment"));
                report.setName((String) s.get("name"));
                report.setCode((String) sub.get("code"));
                report.setGrade((String) sub.get("grade"));
                reportData.add(report);
            });
        });
        return reportData;
    }

    private List<Map<String, Object>> parseJSONToMap(String jsonString) throws ScriptException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("javascript");
        String script = "Java.asJSONCompatible(" + jsonString + ")";
        Map<String, Object> result = (Map<String, Object>) scriptEngine.eval(script);
        return (List<Map<String, Object>>) result.get("report");
    }

    public static String getJSONFromSInputStream(InputStream in) {
        Scanner sc = new Scanner(in);
        String jsonString = EMPTY_STRING;
        while (sc.hasNextLine()) {
            jsonString += sc.nextLine();
        }
        jsonString = jsonString.replace("\n", "").replace("\t", "");
        return jsonString;
    }
}

class Report {
    private String enrollment;
    private String name;
    private String code;
    private String grade;

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
