package eltry;

/**
 * Represents a parsed user command with its associated arguments.
 * Used by the parser to encapsulate the action and relevant parameters.
 */
public class Command {

    /** The primary action/command keyword (e.g., "todo", "deadline", "list"). */
    public final String action;

    /** The index associated with commands that operate on a task (e.g., mark/unmark/delete). */
    public final int index;

    /** First argument for commands that require additional input. */
    public final String arg1;

    /** Second argument for commands that require additional input. */
    public final String arg2;

    /** Third argument for commands that require additional input. */
    public final String arg3;

    /**
     * Creates a command with only an action and no arguments.
     *
     * @param action primary action keyword
     */
    public Command(String action) {
        this(action, -1, null, null, null);
    }

    /**
     * Creates a command with an action and a task index.
     *
     * @param action primary action keyword
     * @param index index of the task (for mark/unmark/delete)
     */
    public Command(String action, int index) {
        this(action, index, null, null, null);
    }

    /**
     * Creates a command with an action and one argument.
     *
     * @param action primary action keyword
     * @param arg1 first argument
     */
    public Command(String action, String arg1) {
        this(action, -1, arg1, null, null);
    }

    /**
     * Creates a command with an action and two arguments.
     *
     * @param action primary action keyword
     * @param arg1 first argument
     * @param arg2 second argument
     */
    public Command(String action, String arg1, String arg2) {
        this(action, -1, arg1, arg2, null);
    }

    /**
     * Creates a command with an action and three arguments.
     *
     * @param action primary action keyword
     * @param arg1 first argument
     * @param arg2 second argument
     * @param arg3 third argument
     */
    public Command(String action, String arg1, String arg2, String arg3) {
        this(action, -1, arg1, arg2, arg3);
    }

    /**
     * Private constructor used by other constructors to initialize all fields.
     *
     * @param action primary action keyword
     * @param index task index
     * @param arg1 first argument
     * @param arg2 second argument
     * @param arg3 third argument
     */
    private Command(String action, int index, String arg1, String arg2, String arg3) {
        this.action = action;
        this.index = index;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }
}
