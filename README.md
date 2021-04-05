## Eager-matcher test task
**Task:**  
You have a function, both text and regex parameters are set by a user. Refactor the function to avoid crash or hang during the code execution.

    public boolean matches(String text, String regex) {
        return Pattern.compile(regex).matcher(text).matches();
    }
**Hint:** you don't want to wait forever till the regex match finishes.

**Solution:**                                                              
Terminate matcher task in case its execution time exceeds the specified limit and add `try` block 
to exit in case `PatternSyntaxException` is thrown.

### How to test?

1. Execute `chmod u+x build-jar.sh` in the root of repository
2. Build `.jar` with `bash build-jar.sh`
3. Launch: execute `java -jar TestMatcher.jar <millis to wait>`, where `<millis to wait>` is
   the time for EagerMatcher to wait before termination.   
   Try 5 ms at first.  

The text in `file.txt` matches the regular expression in the code. If the matching takes too 
long, `matches()` returns `false`.
