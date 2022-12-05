package utils;

import java.util.List;

public abstract class AdventDay {

    /**
     * The raw input.
     */
    protected String input;

    /**
     * Calculates the answer to part 1 of the day's puzzle.
     */
    public abstract String part1();

    /**
     * Calculates the answer to part 2 of the day's puzzle.
     */
    public abstract String part2();

    /**
     * Reads the input from either a file or from the website directly and stores it in the input field.
     */
    public void initialize() {
        Day annotation = getClass().getAnnotation(Day.class);

        if (annotation.test()) {
            input = Reader.readStringFromFile(annotation.day());
        } else {
            input = Reader.getInput(annotation.day(), 2022);
        }
    }

    /**
     * Returns the day number.
     */
    public int getDay() {
        return getClass().getAnnotation(Day.class).day();
    }

    protected List<Character> convertToCharList(String s) {
        return s.chars().mapToObj(c -> (char) c).toList();
    }

    protected String[] splitInHalf(String s) {
        return new String[]{s.substring(0, s.length() / 2), s.substring(s.length() / 2)};
    }
}