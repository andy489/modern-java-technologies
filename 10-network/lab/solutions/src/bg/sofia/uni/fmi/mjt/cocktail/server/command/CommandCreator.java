package bg.sofia.uni.fmi.mjt.cocktail.server.command;

import java.util.ArrayList;
import java.util.List;

public class CommandCreator {

    public static Command newCommand(String clientInput) {
        var tokens = getCommandArguments(clientInput);
        var args = tokens.subList(1, tokens.size()).toArray(String[]::new);
        return new Command(tokens.get(0), args);
    }

    private static List<String> getCommandArguments(String input) {
        var tokens = new ArrayList<String>();
        var sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == ' ') {
                if (!sb.isEmpty()) {
                    tokens.add(sb.toString());
                    sb.setLength(0);
                }
            } else if (c != '\n' && c != '\r') {
                sb.append(c);
            }
        }
        if (!sb.isEmpty()) {
            tokens.add(sb.toString());
        }

        return tokens;
    }
}
