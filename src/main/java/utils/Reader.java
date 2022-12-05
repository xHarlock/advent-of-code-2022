package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class Reader {

    /**
     * Reads the input from a txt file and returns it.
     *
     * @param day Day of advent.
     * @return The input as a String.
     */
    public static String readStringFromFile(int day) {
        String path = String.format("./src/main/resources/day_%02d.txt", day);
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Reads all the lines from a txt file and returns them as String[] array.
     *
     * @param day Day of advent.
     * @return String[] array that contains the lines from the file.
     */
    public static String[] readLinesFromFile(int day) {
        String path = String.format("./src/main/resources/day_%02d.txt", day);
        Scanner scanner;

        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + path);
        }

        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();
        return lines.toArray(new String[0]);
    }

    public static String[] readLines(int day) {
        String path = String.format("./src/main/resources/day_%02d.txt", day);
        String[] lines;

        try (Stream<String> stream = Files.lines(Path.of(path))) {
            lines = stream.toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return lines;
    }

    /**
     * Reads all integers from a txt file and returns them as int[] array.
     *
     * @param day Day of advent.
     * @return int[] array containing the integers from the file.
     */
    public static int[] readIntegersFromFile(int day) {
        String path = String.format("./src/main/resources/day_%02d.txt", day);
        Scanner scanner;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + path);
        }
        List<Integer> ints = new ArrayList<>();
        while (scanner.hasNextInt()) {
            ints.add(scanner.nextInt());
        }
        scanner.close();
        return ints.stream().mapToInt(k -> k).toArray();
    }

    /**
     * Method that reads the input from the Advent of Code website and returns it as a String.
     *
     * @param day  Day of advent.
     * @param year Year of the event.
     * @return String with the input.
     */
    public static String getInput(int day, int year) {
        CookieHandler.setDefault(new CookieManager());
        HttpCookie cookie = new HttpCookie("session", getCookie());
        cookie.setPath("/");
        cookie.setVersion(0);
        String body;
        try {
            ((CookieManager) CookieHandler.getDefault()).getCookieStore().add(new URI("https://adventofcode.com"), cookie);
            HttpClient client = HttpClient.newBuilder().cookieHandler(CookieHandler.getDefault()).connectTimeout(Duration.ofSeconds(10)).build();
            HttpRequest req = HttpRequest.newBuilder().uri(URI.create("https://adventofcode.com/" + year + "/day/" + day + "/input")).GET().build();
            body = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException("Error while reading input from website: " + e.getMessage());
        }
        return body;
    }

    private static String getCookie() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("cookie.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Text file with cookie not found!");
        }
        if (!scanner.hasNextLine()) {
            throw new RuntimeException("Text file with cookie is empty!");
        }
        return scanner.nextLine();
    }
}