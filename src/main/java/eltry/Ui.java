package eltry;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interactions in the terminal.
 * Provides methods to display messages, show task updates, and read user input.
 */
public class Ui {

    /** Scanner object to read input from the user. */
    private final Scanner scanner;

    /**
     * Constructs a new Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message including the logo and program name.
     *
     * @param logo ASCII art logo
     * @param name name of the bot
     */
    public void showWelcome(String logo, String name) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm " + name + ", your friendly task bot.");
        System.out.println(" Type tasks to add them, 'list' to view tasks, 'mark <num>' to mark done,");
        System.out.println(" 'unmark <num>' to undo, 'delete <num>' to remove, and 'bye' to exit!");
        System.out.println(" Task types: todo <desc>, deadline <desc> /by <yyyy-MM-dd HHmm>,");
        System.out.println("           event <desc> /from <start> /to <end>");
        System.out.println(line);
    }

    /** Displays a goodbye message when the program exits. */
    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon! 😊");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to show
     */
    public void showError(String message) {
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows a message that a task has been added.
     *
     * @param task the task that was added
     * @param size the total number of tasks after addition
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println(" Got it. I've added this task:\n   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows a message that a task has been deleted.
     *
     * @param task the task that was removed
     * @param size the total number of tasks after removal
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println(" Noted. I've removed this task:\n   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows that a task has been marked as done.
     *
     * @param task the task that was marked
     */
    public void showTaskMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:\n   " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows that a task has been unmarked (not done).
     *
     * @param task the task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:\n   " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the full list of tasks.
     *
     * @param tasks the list of tasks to display
     * @throws EltryException if the task list is empty
     */
    public void showTaskList(ArrayList<Task> tasks) throws EltryException {
        if (tasks.isEmpty()) throw new EltryException("Your task list is empty.");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads a command from the user.
     *
     * @return the trimmed input string entered by the user
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /** Closes the scanner to release resources. */
    public void close() {
        scanner.close();
    }
}
