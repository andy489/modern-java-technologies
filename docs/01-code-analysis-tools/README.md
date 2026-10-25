# Static Code Analysis Tools

## Checkstyle ([GitHub](https://github.com/checkstyle/checkstyle))

### From the Command Line

Download the latest checkstyle release from [GitHub releases](https://github.com/checkstyle/checkstyle/releases).

```bash
# point to the course checkstyle rules
export CHECKS=https://raw.githubusercontent.com/fmi/java-course/master/docs/01-code-analysis-tools/checkstyle/checkstyle.xml

# run for a single .java file
java -jar checkstyle-9.1-all.jar -c $CHECKS Main.java

# run for the whole project
java -jar checkstyle-9.1-all.jar -c $CHECKS my-fancy-project/
```

### From IntelliJ IDEA

1. **File** → **Settings...** → **Plugins** → **Marketplace**
2. Search for `CheckStyle-IDEA` → **Install**
3. Restart the IDE

To configure the rules:

1. **File** → **Settings...** → **Tools** → **Checkstyle**
2. The **Configuration File** section shows two pre-installed configurations: _Sun Checks_ and _Google Checks_.
3. To add a custom configuration, click **+**, set a description such as `MJT`, and choose whether to use a local or remote file:
   - **Use a local Checkstyle file** → **Browse** — download [this file](https://github.com/andy489/Modern_Java_Technologies/blob/main/docs/01-code-analysis-tools/checkstyle/checkstyle.xml) from the course repository.
   - **Use a Checkstyle file accessible via HTTP** — enter the URL of the remote file, for example [this one](https://raw.githubusercontent.com/andy489/Modern_Java_Technologies/main/docs/01-code-analysis-tools/checkstyle/checkstyle.xml) from the course grader.

To make the IDE's auto-formatter (`Ctrl+Alt+L`) use the same rules:

1. **File** → **Settings...** → **Editor** → **Code Style** → **Java**
2. In the **Scheme** field, click the gear icon → **Import Scheme** → **Checkstyle Configuration** and select the configuration file.

### From Eclipse

1. **Help** → **Eclipse Marketplace...**
2. Find and install the [Checkstyle Plug-in](https://marketplace.eclipse.org/content/checkstyle-plug).

To enable checkstyle for a project:

1. **my-fancy-project** → **Properties** → **Checkstyle**
2. Check **Checkstyle active for this project**.
3. **Local Check Configurations...** → **New...**
4. Set **Type** to **Remote Configuration**.
5. Give it a name, e.g. `mjt`.
6. Paste [this link](https://www.dropbox.com/s/l5tuq4qsisiymsu/checkstyle.xml?dl=0) as the **Location** and click **OK**.
7. Go back to **Main** and select the checkstyle configuration you just created.
8. Click **Apply and Close**.
9. Checkstyle messages should now appear in the editor.
