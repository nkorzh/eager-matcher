import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class TestMatcher {
    public static void main(String[] args) {
        int millisToWait = 1;
        if (args != null && args.length >= 1 && args[0] != null) {
            try {
                millisToWait = Integer.parseInt(args[0]);
            } catch (final NumberFormatException e) {
                System.out.println("Couldn't parse number as first argument");
            }
        }
        System.out.println("==Testing EagerMatcher with " + millisToWait + " millisecond(s) time-limit==");
        final String text;
        try (final BufferedReader reader = Files.newBufferedReader(Path.of("file.txt"))) {
            text = reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            System.out.println("Error reading file");
            return;
        }
        // insert milliseconds to Reg constructor to test if the matching will be interrupted
        // the text from repository matches given regex
        final String regex = "(x+x+)y";
        if (new EagerMatcher(millisToWait).matches(text, regex)) {
            System.out.println("Text matches regexp, time limit is enough, try reduce the limit");
            System.out.println("Test failed.");
        } else {
            System.out.println("Text did not match, matching has been interrupted");
            System.out.println("Success.");
        }
    }
}
