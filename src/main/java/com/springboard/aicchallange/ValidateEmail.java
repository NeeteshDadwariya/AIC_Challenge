package com.springboard.aicchallange;

import java.util.Scanner;
import java.util.function.Predicate;

public class ValidateEmail {
    private static final String ALPHA_NUMERIC_REGEXP = "^[a-zA-Z0-9]+$";
    private static final String ALPHA_REGEXP = "^[a-zA-Z]+$";
    private static final String ALPHA_NUMERIC_REGEXP_FIRST_LETTER = "^[a-zA-Z][a-zA-Z0-9]+$";
    private static final String AT_THE_RATE = "@";
    private static final String INVALID = "Invalid";
    private static final String VALID = "Valid";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine().trim();

        //Evaluate and print result.
        new ValidateEmail().printResult(email);
    }

    public void printResult(String email) {
        String segments[] = email.split(AT_THE_RATE);

        //If the string has not 2 segments after split with '@', then it is invalid.
        if (segments.length != 2) {
            System.out.println(INVALID);
            return;
        }

        boolean isValidFirstSegment = validateFirstSegment().test(segments[0]);
        boolean isValidSecondSegment = validateSecondSegment().test(segments[1]);

        if (!isValidFirstSegment || !isValidSecondSegment) {
            System.out.println(INVALID);
        } else {
            System.out.println(VALID);
        }

    }

    private static Predicate<String> validateFirstSegment() {
        //First segment should be only alphanumeric with first letter being alphabetical only.
        return firstSegment -> firstSegment != null && firstSegment.matches(ALPHA_NUMERIC_REGEXP_FIRST_LETTER);
    }

    private static Predicate<String> validateSecondSegment() {
        return secondSegment -> {
            if (secondSegment == null || secondSegment.isEmpty()) {
                return false;
            }
            String segments[] = secondSegment.split("\\.");

            if (segments.length > 3) {
                return false;
            }

            for (int i = 0; i < segments.length; i++) {
                String seg = segments[i];
                //If last segment
                if (i == segments.length - 1) {
                    //Lengths of last sub strings in second segments should be 2 or 3.
                    if (seg.length() < 2 || seg.length() > 3) {
                        return false;
                    }

                    //last sub segment should be alphabetical only.
                    if (!seg.matches(ALPHA_REGEXP)) {
                        return false;
                    }
                }
                //If not last segment - then can be alphanumeric only.
                else if (!seg.matches(ALPHA_NUMERIC_REGEXP)) {
                    return false;
                }
            }
            return true;
        };
    }
}
