package days;

import utils.AdventDay;
import utils.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Day(day = 6)
public class Day06 extends AdventDay {

    @Override
    public String part1() {
        return String.valueOf(findMarker(4));
    }

    @Override
    public String part2() {
        return String.valueOf(findMarker(14));
    }

    private int findMarker(int markerSize) {
        List<String> chars = new ArrayList<>();
        String[] charArray = input.split("");

        int markerIndex = -1;

        for (int i = 0; i < charArray.length; i++) {
            chars.add(charArray[i]);
            if (chars.size() == markerSize) {
                if (isMarker(chars)) {
                    markerIndex = i + 1;
                    break;
                }
                chars.remove(0);
            }
        }
        return markerIndex;
    }

    private boolean isMarker(List<String> chars) {
        Set<String> set = new HashSet<>(chars);
        return set.size() == chars.size();
    }
}