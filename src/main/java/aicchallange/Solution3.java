package aicchallange;

import java.util.Scanner;
import java.util.function.Predicate;

public class Solution3 {
    private static final String ALPHA_NUMERIC_REGEXP = "^[a-zA-Z0-9]+$";
    private static final String ALPHA_REGEXP = "^[a-zA-Z]+$";
    private static final String ALPHA_NUMERIC_REGEXP_FIRST_LETTER = "^[a-zA-Z][a-zA-Z0-9]+$";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //String email = sc.nextLine();
        new Solution3().printResult("neeteshdadwariya123@gmail-com.insff");
    }

    private void printResult(String email) {

        String segments[] = email.split("@");
        Predicate<String> firstSegmentValidator = new FirstSegmentValidator();
        Predicate<String> secondSegmentValidator = new SecondSegmentValidator();

        if (segments.length != 2 || !firstSegmentValidator.test(segments[0]) || !secondSegmentValidator.test(segments[1])) {
            System.out.println("Invalid");
        } else {
            System.out.println("Valid");
        }

    }

    class FirstSegmentValidator implements Predicate<String> {

        @Override
        public boolean test(String firstSegment) {
            return firstSegment != null && firstSegment.matches(ALPHA_NUMERIC_REGEXP_FIRST_LETTER);
        }
    }

    class SecondSegmentValidator implements Predicate<String> {

        @Override
        public boolean test(String s) {
            if (s == null || s.isEmpty()) {
                return false;
            }
            String segments[] = s.split("\\.");

            if (segments.length > 3) {
                return false;
            }

            for (int i = 0; i < segments.length; i++) {
                String seg = segments[i];
                //If last segment
                if (i == segments.length - 1) {
                    if (seg.length() < 2 || seg.length() > 3) {
                        return false;
                    }

                    if (!seg.matches(ALPHA_REGEXP)) {
                        return false;
                    }
                }
                //If not last segment
                else if (!seg.matches(ALPHA_NUMERIC_REGEXP)) {
                    return false;
                }
            }
            return true;
        }
    }
}
