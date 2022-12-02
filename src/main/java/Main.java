import days.Day01;
import days.Day02;
import utils.AdventDay;

import java.util.List;

@SuppressWarnings("unused")
public class Main {

    private static List<AdventDay> days;

    public static void main(String[] args) {
        days = List.of(new Day01(), new Day02());

//        printAll();
        print(2);
    }

    private static void printAll() {
        for (AdventDay day : days) {
            day.initialize();
            System.out.println("Day " + day.getDay());
            System.out.println("Part 1: " + day.part1());
            System.out.println("Part 2: " + day.part2() + "\n");
        }
    }

    private static void print(int dayNo) {
        AdventDay day = days.get(dayNo - 1);
        day.initialize();
        System.out.println("Day " + day.getDay());
        System.out.println("Part 1: " + day.part1());
        System.out.println("Part 2: " + day.part2());
    }
}