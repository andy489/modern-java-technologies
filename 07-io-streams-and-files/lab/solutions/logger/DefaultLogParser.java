package bg.sofia.uni.fmi.mjt.logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DefaultLogParser implements LogParser {

    private static final String LOG_TOKEN_DELIMITER = "\\|";
    private static final int LOG_LEVEL_TOKEN = 0;
    private static final int LOG_TIMESTAMP_TOKEN = 1;
    private static final int LOG_PACKAGE_TOKEN = 2;
    private static final int LOG_MESSAGE_TOKEN = 3;

    private final Path logFilePath;

    public DefaultLogParser(Path logsFilePath) {
        this.logFilePath = logsFilePath;
    }

    @Override
    public List<Log> getLogs(Level level) {
        checkNull(level, "level");
        return getAllLogs().stream()
                .filter(log -> log.level() == level)
                .toList();
    }

    @Override
    public List<Log> getLogs(LocalDateTime from, LocalDateTime to) {
        checkNull(from, "from");
        checkNull(to, "to");
        return getAllLogs().stream()
                .filter(log -> log.timestamp().isAfter(from) && log.timestamp().isBefore(to))
                .toList();
    }

    @Override
    public List<Log> getLogsTail(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n cannot be a negative number");
        }
        var allLogs = getAllLogs();
        return allLogs.subList(Math.max(allLogs.size() - n, 0), allLogs.size());
    }

    private List<Log> getAllLogs() {
        var logs = new ArrayList<Log>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(logFilePath)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logs.add(parseLog(line));
            }
        } catch (IOException e) {
            throw new LogException("A problem occurred while reading from a file", e);
        }
        return logs;
    }

    private Log parseLog(String line) {
        var lineTokens = line.split(LOG_TOKEN_DELIMITER);
        for (int i = 0; i < lineTokens.length; i++) {
            lineTokens[i] = lineTokens[i].trim();
        }
        Level level = Level.valueOf(lineTokens[LOG_LEVEL_TOKEN].substring(1, lineTokens[0].length() - 1));
        LocalDateTime timestamp = LocalDateTime.parse(lineTokens[LOG_TIMESTAMP_TOKEN], DateTimeFormatter.ISO_DATE_TIME);
        String packageName = lineTokens[LOG_PACKAGE_TOKEN];
        String message = lineTokens[LOG_MESSAGE_TOKEN];
        return new Log(level, timestamp, packageName, message);
    }

    private void checkNull(Object o, String field) {
        if (o == null) {
            throw new IllegalArgumentException(String.format("%s cannot be null", field));
        }
    }
}
