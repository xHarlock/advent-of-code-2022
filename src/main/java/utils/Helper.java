package utils;

public final class Helper {

    private Helper() {
    }

    public static boolean isDigit(String s) {
        return s.matches("\\d+");
    }
}