package com.trello;

public class Waiters extends WebDriverSettings{

    public static void waitForNSeconds(int n, String reason) {
        waitMilliseconds(n * 1000, reason);
    }

    public static void waitForNSeconds(int n) {
        waitForNSeconds(n, "wait");
    }

    public static void waitMilliseconds(int milliseconds, String reason) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new Error("got interrupted:" + e.getMessage(), e);
        }
    }
}
