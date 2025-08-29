package eltry;

/**
 * Handles parsing of user input strings into {@link Command} objects.
 * Validates input format and throws {@link EltryException} for invalid commands.
 */
public class Parsers {

    /**
     * Parses a user input string into a {@link Command}.
     *
     * @param input the raw user input string
     * @return the parsed Command object
     * @throws EltryException if the input is invalid or the format is incorrect
     */
    public Command parse(String input) throws EltryException {
        if (input.equalsIgnoreCase("bye")) {
            return new Command("bye");
        } else if (input.equalsIgnoreCase("list")) {
            return new Command("list");
        } else if (input.toLowerCase().startsWith("mark ")) {
            int index = parseIndex(input);
            return new Command("mark", index);
        } else if (input.toLowerCase().startsWith("unmark ")) {
            int index = parseIndex(input);
            return new Command("unmark", index);
        } else if (input.toLowerCase().startsWith("delete ")) {
            int index = parseIndex(input);
            return new Command("delete", index);
        } else if (input.toLowerCase().startsWith("todo")) {
            String desc = input.substring(4).trim();
            if (desc.isEmpty()) throw new EltryException("The description of a todo cannot be empty.");
            return new Command("todo", desc);
        } else if (input.toLowerCase().startsWith("deadline")) {
            String rest = input.substring(8).trim();
            int byIndex = rest.indexOf("/by");
            if (byIndex == -1 || byIndex == 0) {
                throw new EltryException("Usage: deadline <desc> /by <yyyy-MM-dd HHmm>");
            }
            String desc = rest.substring(0, byIndex).trim();
            String byStr = rest.substring(byIndex + 3).trim();
            if (desc.isEmpty() || byStr.isEmpty()) {
                throw new EltryException("Description and deadline cannot be empty.");
            }
            return new Command("deadline", desc, byStr);
        } else if (input.toLowerCase().startsWith("event")) {
            String rest = input.substring(5).trim();
            int fromIndex = rest.indexOf("/from");
            int toIndex = rest.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
                throw new EltryException("Usage: event <desc> /from <start> /to <end>");
            }
            String desc = rest.substring(0, fromIndex).trim();
            String fromStr = rest.substring(fromIndex + 5, toIndex).trim();
            String toStr = rest.substring(toIndex + 4).trim();
            if (desc.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
                throw new EltryException("Description, start, and end cannot be empty.");
            }
            return new Command("event", desc, fromStr, toStr);
        } else {
            throw new EltryException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses the index number from a mark/unmark/delete command.
     *
     * @param input the raw command input
     * @return zero-based index of the task
     * @throws EltryException if the index is missing, not a number, or invalid
     */
    private int parseIndex(String input) throws EltryException {
        try {
            String[] parts = input.split(" ", 2);
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0) throw new EltryException("Invalid task number.");
            return index;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new EltryException("Usage: mark/unmark/delete <task_number>");
        }
    }
}
