package days;

import utils.AdventDay;
import utils.Day;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;

@Day(day = 4)
public class Day04 extends AdventDay {

    @Override
    public String part1() {
        List<Pair<Elf, Elf>> pairs = getPairs();

        int counter = 0;

        for (Pair<Elf, Elf> pair : pairs) {
            Elf first = pair.getFirst();
            Elf second = pair.getSecond();

            if (first.contains(second) || second.contains(first)) {
                counter++;
            }
        }
        return String.valueOf(counter);
    }

    @Override
    public String part2() {
        List<Pair<Elf, Elf>> pairs = getPairs();

        int counter = 0;

        for (Pair<Elf, Elf> pair : pairs) {
            Elf first = pair.getFirst();
            Elf second = pair.getSecond();

            if (first.overlaps(second) || second.overlaps(first)) {
                counter++;
            }
        }
        return String.valueOf(counter);
    }

    private List<Pair<Elf, Elf>> getPairs() {
        String[] lines = input.split("\n");
        List<Pair<Elf, Elf>> pairs = new ArrayList<>();

        for (String line : lines) {
            String[] elves = line.strip().split(",");
            pairs.add(new Pair<>(new Elf(elves[0]), new Elf(elves[1])));
        }
        return pairs;
    }

    private static class Elf {
        private final int start;
        private final int end;

        public Elf(String assignment) {
            String[] parts = assignment.split("-");
            this.start = Integer.parseInt(parts[0]);
            this.end = Integer.parseInt(parts[1]);
        }

        public boolean contains(Elf other) {
            return this.start <= other.start && this.end >= other.end;
        }

        public boolean overlaps(Elf other) {
            return this.start <= other.end && this.end >= other.start;
        }
    }
}