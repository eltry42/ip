package eltry;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Handles user interactions and formats messages for display.
 * Can be used for CLI or GUI output.
 */
public class Ui {

    /** Introduction message shown at program start. */
    private static final String INTRO = "Hello! I'm Eltry!\nHow can I help you?";

    /** Farewell message shown at program exit. */
    private static final String BYE = "Bye! See you again!\n";

    /** Output stream for displaying messages. */
    private final PrintStream out;

    /**
     * Constructs a Ui with default System.in and System.out streams.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a Ui with custom input and output streams.
     *
     * @param in input stream
     * @param out output stream
     */
    public Ui(InputStream in, PrintStream out) {
        this.out = out;
    }

    /**
     * Returns the introduction message.
     *
     * @return welcome message
     */
    public String getIntro() {
        return INTRO;
    }

    /**
     * Returns the farewell message.
     *
     * @return goodbye message
     */
    public String getBye() {
        return BYE;
    }

    /**
     * Returns a message confirming a task was added.
     *
     * @param task the task added
     * @param tasks the current task list
     * @return formatted message
     */
    public String showTaskAdded(Task task, TaskList tasks) {
        return String.format("I have added task: %s\nNow you have %d tasks in the list.",
                task, tasks.size());
    }

    /**
     * Returns a message confirming a task was deleted.
     *
     * @param task the task deleted
     * @param tasks the current task list
     * @return formatted message
     */
    public String showTaskDeleted(Task task, TaskList tasks) {
        return String.format("I have removed this task.\n%s\nNow you have %d tasks in the list.",
                task, tasks.size());
    }

    /**
     * Returns a message confirming a task was marked as done.
     *
     * @param task the task marked
     * @return formatted message
     */
    public String showTaskMarked(Task task) {
        return String.format("Nice! I've marked this task as complete!\n%s", task);
    }

    /**
     * Returns a message confirming a task was marked as not done.
     *
     * @param task the task unmarked
     * @return formatted message
     */
    public String showTaskUnmarked(Task task) {
        return String.format("Okay, I've marked this task as not done yet:\n%s", task);
    }

    /**
     * Returns a formatted list of all tasks.
     *
     * @param tasks the task list
     * @return formatted task list or empty message
     * @throws EltryException if accessing tasks fails
     */
    public String showTasks(TaskList tasks) throws EltryException {
        if (tasks.size() == 0) return "There are no tasks in the list.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return sb.toString().trim();
    }

    /**
     * Returns a formatted list of found tasks matching a search query.
     *
     * @param found list of found tasks
     * @return formatted message
     */
    public String showFoundTasks(ArrayList<Task> found) {
        if (found.isEmpty()) return "No tasks found matching your query.";
        StringBuilder sb = new StringBuilder("Found tasks:\n");
        for (int i = 0; i < found.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, found.get(i)));
        }
        return sb.toString().trim();
    }

    /**
     * Returns an error message.
     *
     * @param message the error message
     * @return formatted error message
     */
    public String showError(String message) {
        return message;
    }
}
