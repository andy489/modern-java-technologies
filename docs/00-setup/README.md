# Environment Setup — Tips & Tricks

- [Java Development Kit](#java-development-kit)
- [Integrated Development Environment](#integrated-development-environment)
    - [IntelliJ IDEA](#intellij-idea)
    - [Eclipse](#eclipse)
- [Online Auto Grader](#online-auto-grader)
    - [Eclipse Plugin for Grader](#eclipse-plugin-for-grader)

## Java Development Kit

- [Install on Windows](https://docs.oracle.com/en/java/javase/17/install/installation-jdk-microsoft-windows-platforms.html#GUID-A740535E-9F97-448C-A141-B95BF1688E6F)
- [Install on Linux](https://docs.oracle.com/en/java/javase/17/install/installation-jdk-linux-platforms.html#GUID-737A84E4-2EFF-4D38-8E60-3E29D1B884B8)
- [Install on macOS](https://docs.oracle.com/en/java/javase/17/install/installation-jdk-macos.html#GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE)

> **Note:** Download JDK 17 from **Builds** for your operating system at [jdk.java.net/17](https://jdk.java.net/17).

Verify that the installation succeeded:

```bash
$ java -version
openjdk version "17" 2021-09-14
OpenJDK Runtime Environment (build 17+35-Ubuntu-120.04)
OpenJDK 64-Bit Server VM (build 17+35-Ubuntu-120.04, mixed mode, sharing)
```

## Integrated Development Environment

You may use any IDE or text editor you prefer. We recommend either _IntelliJ IDEA_ or _Eclipse_.

### IntelliJ IDEA

The free (Community) edition of [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) is fully sufficient for this course.

#### Installation and Hello World

- The official IntelliJ IDEA documentation provides detailed [installation steps](https://www.jetbrains.com/help/idea/installation-guide.html).
- A [short, useful video](https://www.youtube.com/watch?time_continue=245&v=c0efB_CKOYo) walks through creating, running, and configuring a Hello World project.
- The same topic in written form: [Creating and running your first Java application](https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-application.html).

#### Useful IntelliJ IDEA Shortcuts

The most commonly used shortcuts are listed in [Mastering IntelliJ IDEA keyboard shortcuts](https://www.jetbrains.com/help/idea/mastering-keyboard-shortcuts.html#d1746384e89).

A brief UI overview along with handy shortcuts is available in [IntelliJ IDEA overview](https://www.jetbrains.com/help/idea/discover-intellij-idea.html).

#### Debugging in IntelliJ IDEA

A beginner's debugging tutorial for _IntelliJ IDEA_ can be found [here](https://www.jetbrains.com/help/idea/debugging-your-first-java-application.html).

#### JUnit Tests

Some problem statements include sample tests. If you see a `Cannot resolve symbol 'Test'` error after adding a test file to your project, you need to add JUnit to the project's build path. The [official IntelliJ IDEA documentation](https://www.jetbrains.com/help/idea/testing.html) explains how to configure JUnit 4/5. Add JUnit 4 via `Alt+Enter on @Test → Add JUnit 4 to classpath`.

To run the tests, open the test file in the IDE and press `Ctrl+Shift+F10`, or right-click → **Run `<filename>`**.

### Eclipse

#### Installation and Hello World

A [detailed tutorial](https://www.vogella.com/tutorials/Eclipse/article.html) for setting up [Eclipse](https://www.eclipse.org/downloads/eclipse-packages/), including installation and creating your first project.

> **Note:** To use Java 17, you need [Eclipse 2021-09](https://www.eclipse.org/downloads/) or later. You also need to install an additional feature available [here](https://marketplace.eclipse.org/content/java-17-support-eclipse-2021-09-421) (drag the **Install** button onto your open workspace to install it).

#### Useful Eclipse Shortcuts

Learning these shortcuts saves a significant amount of time and effort.

##### Editing

| Shortcut | Action |
|---|---|
| `Ctrl + Space` | Auto-completes variable and method names |
| `Ctrl + Shift + F` | Formats your code |
| `Ctrl + O` | Organises imports |
| `Ctrl + D` | Deletes the current line |
| `Alt + Up/Down` | Moves the current line (or selection) up or down |
| `Ctrl + 7` | Comments / uncomments selected lines or blocks |

##### Navigation

| Shortcut | Action |
|---|---|
| `Ctrl + N` | Opens the New Type wizard to create a project, class, or interface |
| `Ctrl + T` | Opens a type — the fastest way to open any class or interface |
| `Ctrl + F7` | Switches between views |
| `Ctrl + F8` | Switches between perspectives (e.g. Java ↔ Debug) |
| `Ctrl + Q` | Jumps to the last edited position in the source |

##### Run & Debug

| Shortcut | Action |
|---|---|
| `Ctrl + F11` | Runs the application |
| `F11` | Runs the application in debug mode |

A comprehensive list is available in the [Eclipse cheatsheet](https://github.com/pellaton/eclipse-cheatsheet).

#### Debugging in Eclipse

An excellent [beginner's debugging guide](https://www.eclipse.org/community/eclipse_newsletter/2017/june/article1.php) for Eclipse.

#### JUnit Tests

Some problem statements include sample tests. If you see a `the import org.junit cannot be resolved` error, you need to add JUnit to the project's build path. You can do this manually by following the steps [here](https://www.tutorialspoint.com/junit/junit_plug_with_eclipse.htm).

To run the tests, right-click the test file → **Run As** → **JUnit Test**.

> **Note:** Tests in this course are written with JUnit 4. Some additional information about JUnit 5 can be found [here](https://www.eclipse.org/community/eclipse_newsletter/2017/october/article5.php).

## Online Auto Grader

The automated testing system is hosted at [grader.sapera.org](http://grader.sapera.org).

To register, contact the administrator. The grader is used for both lab exercises and homework assignments. Submit your solutions via the web interface.

### Eclipse Plugin for Grader

If you use Eclipse, you can submit solutions directly from the IDE using the grader integration plugin.

#### Installation

1. Open _Eclipse_.
2. Go to **Help** → **Install New Software...**.
3. In the **Work with...** field, enter `http://web-cat.org/eclstats/`.
4. Select the **Web-CAT Electronic Assignments Feature** checkbox and click **Next**.
5. Wait for the dialog to load, then click **Next** again.
6. Select **I accept the terms of the license agreements** and click **Finish**.
7. The download and installation will take a moment. If a popup warns about unsigned content, click **Install Anyway**.
8. When installation finishes, agree to restart Eclipse.

#### Configuration

1. Open _Eclipse_.
2. Go to **Window** → **Preferences**.
3. In the left panel, select **Configured Assignments**.
4. In both the **Download URL** and **Submit URL** fields, enter `http://grader.sapera.org/WebObjects/Web-CAT.woa/wa/assignments/eclipse` and click **Apply and Close**.

#### Usage

1. In the **Navigator** panel, select the project you want to submit.
2. Go to **Project** → **Submit Assignment...**.
3. In the **Submit An Assignment for Grading** dialog, select the assignment and click **Finish**.
4. In the authentication dialog, enter your grader username and password. You may select **Remember my password for the rest of this session**. Click **OK**.
5. A browser window will open and, after a few seconds, display your grade.
6. Repeat steps 2–5 until you are satisfied with your result or exhaust the submission limit (if one applies).
