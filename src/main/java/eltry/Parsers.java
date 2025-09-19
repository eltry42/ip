package eltry;

import java.util.Locale;

/**
 * Handles parsing of user input strings into Command objects.
 * Validates input and throws EltryException for invalid formats.
 */
public class Parsers {

    /**
     * Parses user input into a Command object.
     *
     * @param input raw user input
     * @return Command object representing the action
     * @throws EltryException if input is invalid
     */
    public static Command parse(String input) throws EltryException {
        if (input == null || input.isBlank()) {
            throw new EltryException("Input cannot be empty.");
        }

        String lower = input.toLowerCase(Locale.ROOT).trim();

        if (lower.equals("bye")) {
            return new Command("bye");
        }

        if (lower.equals("list")) {
            return new Command("list");
        }

        if (lower.startsWith("mark ")) {
            int index = parseIndex(input, "mark");
            return new Command("mark", index);
        }

        if (lower.startsWith("unmark ")) {
            int index = parseIndex(input, "unmark");
            return new Command("unmark", index);
        }

        if (lower.startsWith("delete ")) {
            int index = parseIndex(input, "delete");
            return new Command("delete", index);
        }

        if (lower.startsWith("todo")) {
            String desc = input.substring(4).trim();
            if (desc.isEmpty()) throw new EltryException("The description of a todo cannot be empty.");
            return new Command("todo", desc);
        }

        if (lower.startsWith("deadline")) {
            String rest = input.substring(8).trim();
            int byIndex = rest.indexOf("/by");
            if (byIndex <= 0) throw new EltryException("Usage: deadline <desc> /by <yyyy-MM-dd HHmm>");
            String desc = rest.substring(0, byIndex).trim();
            String by = rest.substring(byIndex + 3).trim();
            if (desc.isEmpty() || by.isEmpty()) throw new EltryException("Description and deadline cannot be empty.");
            return new Command("deadline", desc, by);
        }

        if (lower.startsWith("event")) {
            String rest = input.substring(5).trim();
            int fromIndex = rest.indexOf("/from");
            int toIndex = rest.indexOf("/to");
            if (fromIndex <= 0 || toIndex <= 0 || fromIndex >= toIndex) {
                throw new EltryException("Usage: event <desc> /from <start> /to <end>");
            }
            String desc = rest.substring(0, fromIndex).trim();
            String from = rest.substring(fromIndex + 5, toIndex).trim();
            String to = rest.substring(toIndex + 3).trim();
            if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new EltryException("Description, start, and end cannot be empty.");
            }
            return new Command("event", desc, from, to);
        }

        if (lower.startsWith("find")) {
            String keyword = input.substring(5).trim();
            if (keyword.isEmpty()) throw new EltryException("Keyword cannot be empty.");
            return new Command("find", keyword);
        }

        throw new EltryException("Unknown command: " + input);
    }

    /**
     * Helper method to parse an index for commands like mark/unmark/delete.
     *
     * @param input raw user input
     * @param command command keyword
     * @return zero-based index
     * @throws EltryException if index is missing or invalid
     */
    private static int parseIndex(String input, String command) throws EltryException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) throw new EltryException("Usage: " + command + " <task_number>");
        try {
            int index = Integer.parseInt(parts[1].trim()) - 1;
            if (index < 0) throw new EltryException("Task number must be positive.");
            return index;
        } catch (NumberFormatException e) {
            throw new EltryException("Invalid task number: " + parts[1]);
        }
    }
}
