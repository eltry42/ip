package eltry;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Ui class handles all user interactions.
 * It returns formatted strings for display (suitable for both CLI and GUI).
 */
public class Ui {

    private static final String INTRO = "Hello! I'm eltry!\n" +
            "How can I help you?";
    private static final String BYE = "Bye! See you again!\n";

    private final PrintStream out;

    /**
     * Constructs a Ui with default System.in and System.out.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a Ui with custom input and output streams.
     *
     * @param in  input stream
     * @param out output stream
     */
    public Ui(InputStream in, PrintStream out) {
        this.out = out;
    }

    /**
     * Returns the introduction message.
     *
     * @return welcome message as string
     */
    public String getIntro() {
        return INTRO;
    }

    /**
     * Returns the goodbye message.
     *
     * @return farewell message as string
     */
    public String getBye() {
        return BYE;
    }

    /**
     * Returns a message confirming a task was deleted.
     *
     * @param task   the task that was removed
     * @param tasks  the current task list
     * @return       formatted message
     */
    public String getDeleteTaskMessage(Task task, ArrayList<Task> tasks) {
        return String.format("I have removed this task.\n%s\n" +
                "Now you have %d tasks in the list.", task, tasks.size());
    }

    /**
     * Returns an error message.
     *
     * @param e the exception containing the error
     * @return  the error message
     */
    public String getErrorMessage(EltryException e) {
        return e.getMessage();
    }

    /**
     * Returns a message confirming a task was marked as done.
     *
     * @param task the task that was marked
     * @return     formatted message
     */
    public String getMarkTaskMessage(Task task) {
        return String.format("Nice! I've marked this task as complete!\n%s", task);
    }

    /**
     * Returns a message confirming a task was added.
     *
     * @param task   the task that was added
     * @param tasks  the current task list
     * @return       formatted message
     */
    public String getAddTaskMessage(Task task, ArrayList<Task> tasks) {
        return String.format("I have added task: %s\n" +
                "Now you have %d tasks in the list.", task, tasks.size());
    }

    /**
     * Returns a formatted list of all tasks.
     *
     * @param tasks the task list to display
     * @return      formatted string of tasks, or empty message
     */
    public String getListTasksMessage(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "There are no tasks in the list";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return sb.toString().trim();
    }

    /**
     * Prompts the user to add a task.
     * (Can be overridden in GUI version)
     */
    public void promptAddTask() {
        out.println("Add Task: ");
    }

    /**
     * Prints a task to the console.
     *
     * @param task the task to print
     */
    public void printTask(Task task) {
        out.println(task);
    }
}
