package days;

import utils.AdventDay;
import utils.Day;

import java.util.ArrayList;
import java.util.List;

@Day(day = 3)
public class Day03 extends AdventDay {

    @Override
    public String part1() {
        List<Integer> priorities = new ArrayList<>();

        for (String rucksack : input.split("\n")) {
            String[] parts = splitInHalf(rucksack);
            List<Character> chars = getCommonChars(parts[0], parts[1]);
            int priority = getPriority(chars.get(0));
            priorities.add(priority);
        }
        int result = priorities.stream().mapToInt(Integer::intValue).sum();
        return String.valueOf(result);
    }

    @Override
    public String part2() {
        List<Integer> priorities = new ArrayList<>();

        String[] elves = input.split("\n");

        for (int i = 0; i < elves.length; i += 3) {
            List<Character> chars = getCommonChars(elves[i], elves[i + 1], elves[i + 2]);
            int priority = getPriority(chars.get(0));
            priorities.add(priority);
        }
        int result = priorities.stream().mapToInt(Integer::intValue).sum();
        return String.valueOf(result);
    }

    /**
     * Returns the priority of the given character. Lowercase letters 'a' through
     * 'z' have priorities 1 through 26. Uppercase letters 'A' through 'Z' have
     * priorities 27 through 52.
     */
    private int getPriority(char c) {
        return Character.isUpperCase(c) ? c - 'A' + 27 : c - 'a' + 1;
    }

    /**
     * Returns a list of characters that are common in all given strings.
     */
    private List<Character> getCommonChars(String... strings) {
        List<Character> chars = new ArrayList<>(convertToCharList(strings[0]));
        for (int i = 1; i < strings.length; i++) {
            chars.retainAll(convertToCharList(strings[i]));
        }
        return chars;
    }
}