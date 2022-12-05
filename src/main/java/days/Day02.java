package days;

import utils.AdventDay;
import utils.Day;

import java.util.ArrayList;
import java.util.List;

@Day(day = 2)
public class Day02 extends AdventDay {

    private List<Shape> opponent;
    private List<Shape> player;

    @Override
    public String part1() {
        initializeShapes(false);
        return String.valueOf(getGameScore());
    }

    @Override
    public String part2() {
        initializeShapes(true);
        return String.valueOf(getGameScore());
    }

    private void initializeShapes(boolean playerSpecial) {
        opponent = new ArrayList<>();
        player = new ArrayList<>();

        for (String line : input.split("\n")) {
            String[] split = line.split(" ");

            Shape o = getShape(split[0].strip());
            opponent.add(o);

            if (playerSpecial) {
                switch (split[1].strip()) {
                    case "X" -> player.add(o.getLosingShape());
                    case "Y" -> player.add(o.getTieShape());
                    case "Z" -> player.add(o.getWinningShape());
                }
            } else {
                player.add(getShape(split[1].strip()));
            }
        }
    }

    private int getGameScore() {
        int score = 0;
        for (int i = 0; i < opponent.size(); i++) {
            Shape o = opponent.get(i);
            Shape p = player.get(i);
            score += getRoundScore(o, p);
        }
        return score;
    }

    private int getRoundScore(Shape opponent, Shape player) {
        int score;
        if (player.beats(opponent)) {
            score = 6;
        } else if (player.losesTo(opponent)) {
            score = 0;
        } else {
            score = 3;
        }
        return score + player.getShapeValue();
    }

    /**
     * Returns the shape corresponding to the given string.
     */
    private Shape getShape(String string) {
        return switch (string) {
            case "A", "X" -> Shape.ROCK;
            case "B", "Y" -> Shape.PAPER;
            case "C", "Z" -> Shape.SCISSORS;
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }

    private enum Shape {
        ROCK, PAPER, SCISSORS;

        public int getShapeValue() {
            return ordinal() + 1;
        }

        public boolean beats(Shape other) {
            return (this == ROCK && other == SCISSORS) ||
                    (this == PAPER && other == ROCK) ||
                    (this == SCISSORS && other == PAPER);
        }

        public boolean losesTo(Shape other) {
            return (this == ROCK && other == PAPER) ||
                    (this == PAPER && other == SCISSORS) ||
                    (this == SCISSORS && other == ROCK);
        }

        /**
         * Returns the shape that loses to this shape.
         */
        public Shape getLosingShape() {
            return switch (this) {
                case ROCK -> SCISSORS;
                case PAPER -> ROCK;
                case SCISSORS -> PAPER;
            };
        }

        /**
         * Returns the shape that beats this shape.
         */
        public Shape getWinningShape() {
            return switch (this) {
                case ROCK -> PAPER;
                case PAPER -> SCISSORS;
                case SCISSORS -> ROCK;
            };
        }

        /**
         * Returns the shape that ties with this shape.
         */
        public Shape getTieShape() {
            return switch (this) {
                case ROCK -> ROCK;
                case PAPER -> PAPER;
                case SCISSORS -> SCISSORS;
            };
        }
    }
}