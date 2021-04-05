import java.util.concurrent.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EagerMatcher {
    private final int millisToWait;

    public EagerMatcher(int millisToWait) {
        this.millisToWait = millisToWait;
    }

    public boolean matches(final String text, final String regex) {
        final Pattern pattern;
        try {
            pattern = Pattern.compile(regex);
        } catch (final PatternSyntaxException e) {
            // log invalid pattern
            return false;
        }
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Boolean> matchTask = executor.submit(() -> pattern.matcher(text).matches());
        boolean doesMatch = false;
        try {
            doesMatch = matchTask.get(millisToWait, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            // matching timed out
        } catch (InterruptedException | ExecutionException e) {
            // handle exceptions
        } finally {
            matchTask.cancel(true);
            executor.shutdownNow();
        }
        return doesMatch;
    }
}
