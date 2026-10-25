# Logger

Professional applications typically use a *logging framework* (or *logger*) to record information — usually to a file — for tracking execution, diagnosing problems, and noting important application events. Using a framework for this purpose makes code more readable, is often more efficient, standardises message formats, and enables automated processing, searching, and filtering of messages.

The JDK ships its own logging framework in the [`java.util.logging`](https://docs.oracle.com/en/java/javase/17/core/java-logging-overview.html) package, and there are many popular open-source Java logging frameworks such as [Log4j](https://logging.apache.org/log4j/2.x/).

We will build a simple logging framework that provides basic capabilities for logging as well as extracting and filtering log messages.

## Logger

In the package `bg.sofia.uni.fmi.mjt.logger`, create a class `DefaultLogger` with the constructor:

```java
public DefaultLogger(LoggerOptions options)
```

that implements the `Logger` interface:

```java
package bg.sofia.uni.fmi.mjt.logger;

import java.nio.file.Path;
import java.time.LocalDateTime;

public interface Logger {

    /**
     * Logs a message to the current log file. If the configured minimum log level
     * is higher than the provided log level, the message is ignored.
     *
     * @param level     the log message severity
     * @param timestamp the time of logging
     * @param message   log message
     * @throws IllegalArgumentException if {@code level} is null, {@code timestamp} is null,
     *                                  or {@code message} is null or empty
     * @throws LogException             if LoggerOptions.shouldThrowErrors() is true and the operation fails.
     *                                  If shouldThrowErrors() is false, any problems are suppressed.
     */
    void log(Level level, LocalDateTime timestamp, String message);

    /**
     * Gets the Logger's options.
     *
     * @return the Logger's options
     */
    LoggerOptions getOptions();

    /**
     * Gets the current log file path.
     *
     * @return the current log file path
     */
    Path getCurrentFilePath();
}
```

## Logger Options

The logger has several mandatory and optional parameters:

- the class being logged from (mandatory)
- the directory name where log files are written (mandatory)
- the maximum size in bytes of a log file (optional, default: 1024 bytes)
- whether to throw or suppress exceptions during logging (optional, default: suppress)
- minimum log level (optional, default: INFO)

Parameters are described by the `LoggerOptions` class in the `bg.sofia.uni.fmi.mjt.logger` package.

## Usage

Example usage of the logging framework:

```java
package com.cryptobank.investment;

public class InvestmentWallet {

    private Logger logger = new DefaultLogger(new LoggerOptions(this.getClass(), "/logs"));

    void buyAsset() {
        logger.log(Level.INFO, LocalDateTime.now(), String.format("Bought asset with ID %s", assetId));
    }
}
```

## Log Files

Log files are text files created in the configured directory. Their names consist of the fixed prefix `"logs-"`, a sequential number starting from 0, and the extension `.txt`. At any point in time, exactly one log file is active (current): the one with the highest sequential number. Files have a fixed maximum size. When attempting to write a message, if the current log file has reached or exceeded its maximum size, a new file with the next sequential number is created, it becomes the active file, and the message is written to it.

## Log Messages

Each line in a log file contains one message in the format:

```text
[level]|timestamp|package|message
```

The four fields, separated by `|` (pipe), represent: the log level, the timestamp in `DateTimeFormatter.ISO_DATE_TIME` format, the package name of the class from which the log was written, and finally the message itself.

Example log file content:

```text
[INFO]|2021-11-27T01:11:03.935601200|com.cryptobank.investment|Bought asset with ID 167305
[WARN]|2021-11-27T01:11:04.099162900|com.cryptobank.investment|Disk space is getting low
[ERROR]|2021-11-27T01:11:04.431274900|com.cryptobank.investment|Failed to process request {1405}
```

A log message is modelled by the `Log` record:

```java
public record Log(Level level, LocalDateTime timestamp, String packageName, String message) { }
```

## Log Level

The log level (also called *log severity*) reflects the importance and priority of a message. The framework supports four levels modelled by the `Level` enum:

```java
public enum Level {
    ERROR(4), WARN(3), INFO(2), DEBUG(1);
}
```

- `ERROR` — a problem affecting correct application behaviour
- `WARN` — something unexpected happened that may be a potential problem
- `INFO` — standard level indicating that an event occurred or the application entered a particular state
- `DEBUG` — information useful for diagnosing and fixing a problem

## Log Parser

To extract and filter messages from the current log file, create a class `DefaultLogParser` with constructor:

```java
public DefaultLogParser(Path logsFilePath)
```

that implements the `LogParser` interface.

## Hint

When used in a regular expression (e.g. as an argument to `String.split()`), the `|` character has special semantics. To match it literally, escape it with a backslash: `\\|`.

## Testing

Start by testing the framework manually, then write unit tests with good code coverage.

## Package Structure

```
src
└─ bg.sofia.uni.fmi.mjt.logger
    ├─ DefaultLogger.java
    ├─ DefaultLogParser.java
    ├─ Level.java
    ├─ Log.java
    ├─ LogException.java
    ├─ Logger.java
    ├─ LoggerOptions.java
    └─ LogParser.java
test
└─ bg.sofia.uni.fmi.mjt.logger
    └─ (your tests here)
```
