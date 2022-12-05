package days;

import utils.AdventDay;
import utils.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Day(day = 1)
public class Day01 extends AdventDay {

    @Override
    public String part1() {
        List<Integer> sums = getSums();
        return String.valueOf(sums.get(0));
    }

    @Override
    public String part2() {
        List<Integer> sums = getSums();
        return String.valueOf(sums.get(0) + sums.get(1) + sums.get(2));
    }

    /**
     * Returns a list of sums of the calories each elf carries.
     */
    private List<Integer> getSums() {
        List<Integer> sums = new ArrayList<>();

        String[] elves = input.split("\n\n");

        for (String elf : elves) {
            int sum = Arrays.stream(elf.split("\n")).mapToInt(Integer::parseInt).sum();
            sums.add(sum);
        }
        sums.sort(Collections.reverseOrder());
        return sums;
    }
}