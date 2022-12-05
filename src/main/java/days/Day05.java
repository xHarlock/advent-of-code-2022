package days;

import utils.AdventDay;
import utils.Day;
import utils.Helper;

import java.util.*;
import java.util.stream.Collectors;

@Day(day = 5)
public class Day05 extends AdventDay {

    private List<Move> moves;
    private List<Stack<String>> stacks;

    @Override
    public String part1() {
        initialize();
        for (Move move : moves) {
            for (int i = 0; i < move.amount; i++) {
                String crate = stacks.get(move.from - 1).pop();
                stacks.get(move.to - 1).push(crate);
            }
        }
        return getCratesAtTop();
    }

    @Override
    public String part2() {
        initialize();
        for (Move op : moves) {
            List<String> cratesToMove = new ArrayList<>();
            for (int i = 0; i < op.amount; i++) {
                cratesToMove.add(stacks.get(op.from - 1).pop());
            }
            Collections.reverse(cratesToMove);
            stacks.get(op.to - 1).addAll(cratesToMove);
        }
        return getCratesAtTop();
    }

    private String getCratesAtTop() {
        return stacks.stream()
                .map(Stack::peek)
                .collect(Collectors.joining());
    }

    @Override
    public void initialize() {
        super.initialize();

        moves = new ArrayList<>();
        int stackCount = 0;

        List<String> crateLines = new ArrayList<>();

        for (String line : input.split("\n")) {
            if (line.isBlank()) {
                continue;
            }

            if (line.contains("move")) {
                moves.add(parseMove(line.strip()));
            } else if (Arrays.stream(line.split(" ")).noneMatch(Helper::isDigit)) {
                crateLines.add(line);
            } else {
                String[] parts = line.strip().split(" ");
                stackCount = Arrays.stream(parts)
                        .filter(Helper::isDigit)
                        .mapToInt(Integer::parseInt)
                        .max().orElseThrow();
            }
        }

        // Initialize stacks and add crates
        stacks = new ArrayList<>();
        for (int i = 0; i < stackCount; i++) {
            stacks.add(new Stack<>());
        }
        Collections.reverse(crateLines);
        for (String s : crateLines) {
            for (int i = 0; i < s.split("").length; i++) {
                if (i % 4 == 1 && s.charAt(i) != ' ') {
                    String crate = String.valueOf(s.charAt(i));
                    stacks.get(i / 4).push(crate);
                }
            }
        }
    }

    private Move parseMove(String line) {
        int[] values = Arrays.stream(line.split(" "))
                .filter(Helper::isDigit)
                .mapToInt(Integer::parseInt)
                .toArray();
        return new Move(values[0], values[1], values[2]);
    }

    private record Move(int amount, int from, int to) {
    }
}