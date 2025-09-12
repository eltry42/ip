package eltry;

import java.util.ArrayList;

/**
 * Represents a parsed user command and its associated arguments.
 * Handles execution of the command on a given TaskList, UI, and Storage.
 */
public class Command {

    /** The primary action or command keyword (e.g., "todo", "deadline", "list"). */
    public final String action;

    /** The index associated with commands that operate on a task (e.g., mark/unmark/delete). */
    public final int index;

    /** The first argument for commands requiring additional input. */
    public final String arg1;

    /** The second argument for commands requiring additional input. */
    public final String arg2;

    /** The third argument for commands requiring additional input. */
    public final String arg3;

    /**
     * Constructs a command with only an action.
     *
     * @param action the command keyword
     */
    public Command(String action) { this(action, -1, null, null, null); }

    /**
     * Constructs a command with an action and a task index.
     *
     * @param action the command keyword
     * @param index the index of the task for index-based commands
     */
    public Command(String action, int index) { this(action, index, null, null, null); }

    /**
     * Constructs a command with an action and a single argument.
     *
     * @param action the command keyword
     * @param arg1 first argument
     */
    public Command(String action, String arg1) { this(action, -1, arg1, null, null); }

    /**
     * Constructs a command with an action and two arguments.
     *
     * @param action the command keyword
     * @param arg1 first argument
     * @param arg2 second argument
     */
    public Command(String action, String arg1, String arg2) { this(action, -1, arg1, arg2, null); }

    /**
     * Constructs a command with an action and three arguments.
     *
     * @param action the command keyword
     * @param arg1 first argument
     * @param arg2 second argument
     * @param arg3 third argument
     */
    public Command(String action, String arg1, String arg2, String arg3) { this(action, -1, arg1, arg2, arg3); }

    /**
     * Private constructor that initializes all fields of a command.
     *
     * @param action the command keyword
     * @param index the task index
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

    /**
     * Executes the command on the given TaskList, updating Storage and returning a UI message.
     *
     * @param tasks the TaskList to operate on
     * @param ui the Ui instance for generating messages
     * @param storage the Storage instance for saving tasks
     * @return a formatted message describing the result of the command
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            switch (action) {
                case "bye": return ui.getBye();
                case "list": return ui.showTasks(tasks);
                case "mark": {
                    Task t = tasks.mark(index);
                    storage.save(tasks);
                    return ui.showTaskMarked(t);
                }
                case "unmark": {
                    Task t = tasks.unmark(index);
                    storage.save(tasks);
                    return ui.showTaskUnmarked(t);
                }
                case "delete": {
                    Task t = tasks.delete(index);
                    storage.save(tasks);
                    return ui.showTaskDeleted(t, tasks);
                }
                case "todo": {
                    Todo t = new Todo(arg1);
                    tasks.add(t);
                    storage.save(tasks);
                    return ui.showTaskAdded(t, tasks);
                }
                case "deadline": {
                    Deadline t = new Deadline(arg1, arg2);
                    tasks.add(t);
                    storage.save(tasks);
                    return ui.showTaskAdded(t, tasks);
                }
                case "event": {
                    Event t = new Event(arg1, arg2, arg3);
                    tasks.add(t);
                    storage.save(tasks);
                    return ui.showTaskAdded(t, tasks);
                }
                case "find": {
                    ArrayList<Task> found = tasks.find(arg1);
                    return ui.showFoundTasks(found);
                }
                default: return ui.showError("Unknown action: " + action);
            }
        } catch (EltryException e) {
            return ui.showError(e.getMessage());
        }
    }
}
