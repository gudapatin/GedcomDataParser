package com.gedcom;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * The class helps the user .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GEDCOMTestSuiteRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(GedcomParserTestSuite.class);

        System.out.println("\nTotal Tests Run = " + result.getRunCount() +
                           "\nTotal Run Time = " + result.getRunTime() + "ms" +
                           "\nTotal Failures = " + result.getFailureCount());

        if(!result.wasSuccessful()) {
            System.out.println("\nFailure details:\n");
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
}