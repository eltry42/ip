// Ui.java
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

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

    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon! 😊");
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println(" Got it. I've added this task:\n   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println(" Noted. I've removed this task:\n   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showTaskMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:\n   " + task);
        System.out.println("____________________________________________________________");
    }

    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:\n   " + task);
        System.out.println("____________________________________________________________");
    }

    public void showTaskList(ArrayList<Task> tasks) throws EltryException {
        if (tasks.isEmpty()) throw new EltryException("Your task list is empty.");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void close() {
        scanner.close();
    }
}