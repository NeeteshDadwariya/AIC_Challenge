package aicchallange;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution2 {

    public static void main(String[] args) throws ScriptException {
        new Solution2().printReport();
    }

    private void printReport() throws ScriptException {
        Scanner sc = new Scanner(System.in);
        String jsonString = "{\n" +
                "    \"report\":[\n" +
                "        {\n" +
                "            \"enrollment\": \"rit2011001\",\n" +
                "            \"name\": \"Julia\",\n" +
                "            \"subject\":[\n" +
                "                {\n" +
                "                    \"code\": \"DSA\",\n" +
                "                    \"grade\": \"A\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"enrollment\": \"rit2011020\",\n" +
                "            \"name\": \"Samantha\",\n" +
                "            \"subject\":[\n" +
                "                {\n" +
                "                    \"code\": \"COM\",\n" +
                "                    \"grade\": \"B\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"code\": \"DSA\",\n" +
                "                    \"grade\": \"A\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        /*while (sc.hasNextLine()) {
            jsonString += sc.nextLine();
        }*/
        jsonString = jsonString.replace("\n", "").replace("\t", "");
        System.out.println(jsonString);
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("javascript");
        String script = "Java.asJSONCompatible(" + jsonString + ")";
        Map<String, Object> result = (Map<String, Object>) scriptEngine.eval(script);
        List<Map<String, Object>> students = (List<Map<String, Object>>) result.get("report");

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

        reportData.forEach(report -> {
            System.out.println(report.getCode() +" "+ report.getGrade() + " " + report.getEnrollment() + " " + report.getName());
        });

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
}
