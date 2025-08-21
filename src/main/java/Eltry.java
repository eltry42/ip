import java.util.ArrayList;
import java.util.Scanner;

class EltryException extends Exception {
    public EltryException(String message) {
        super(message);
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

public class Eltry {
    public static void main(String[] args) {
        String chatbotName = "Eltry";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String logo = """
  _____ _   _____ ______   __
 | ____| | |_   _|  _ \\ \\ / /
 |  _| | |   | | | |_) \\ V / 
 | |___| |___| | |  _ < | |  
 |_____|_____|_| |_| \\_\\|_|            
""";

        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm " + chatbotName + ", your friendly task bot.");
        System.out.println(" Type tasks to add them, 'list' to view tasks, 'mark <num>' to mark done, 'unmark <num>' to undo, 'delete <num>' to remove, and 'bye' to exit!");
        System.out.println(" Task types: todo <desc>, deadline <desc> /by <date>, event <desc> /from <start> /to <end>");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine().trim();
            System.out.println("____________________________________________________________");

            try {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(" Bye. Hope to see you again soon! 😊");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    if (tasks.isEmpty()) {
                        throw new EltryException("Your task list is empty.");
                    }
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                } else if (input.toLowerCase().startsWith("mark ")) {
                    int index = parseIndex(input, tasks.size());
                    tasks.get(index).markAsDone();
                    System.out.println(" Nice! I've marked this task as done:\n   " + tasks.get(index));
                } else if (input.toLowerCase().startsWith("unmark ")) {
                    int index = parseIndex(input, tasks.size());
                    tasks.get(index).markAsNotDone();
                    System.out.println(" OK, I've marked this task as not done yet:\n   " + tasks.get(index));
                } else if (input.toLowerCase().startsWith("delete ")) {
                    int index = parseIndex(input, tasks.size());
                    Task removed = tasks.remove(index);
                    System.out.println(" Noted. I've removed this task:\n   " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.toLowerCase().startsWith("todo")) {
                    String desc = input.substring(4).trim();
                    if (desc.isEmpty()) {
                        throw new EltryException("The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(desc));
                    System.out.println(" Got it. I've added this task:\n   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.toLowerCase().startsWith("deadline")) {
                    String[] parts = input.substring(8).split("/by");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new EltryException("Usage: deadline <desc> /by <date>");
                    }
                    tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
                    System.out.println(" Got it. I've added this task:\n   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.toLowerCase().startsWith("event")) {
                    String[] parts = input.substring(5).split("/from");
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        throw new EltryException("Usage: event <desc> /from <start> /to <end>");
                    }
                    String[] times = parts[1].split("/to");
                    if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                        throw new EltryException("Usage: event <desc> /from <start> /to <end>");
                    }
                    tasks.add(new Event(parts[0].trim(), times[0].trim(), times[1].trim()));
                    System.out.println(" Got it. I've added this task:\n   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new EltryException("I'm sorry, but I don't know what that means.");
                }
            } catch (EltryException e) {
                System.out.println(" " + e.getMessage());
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }

    private static int parseIndex(String input, int size) throws EltryException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= size) {
                throw new EltryException("Invalid task number.");
            }
            return index;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new EltryException("Usage: mark/unmark/delete <task_number>");
        }
    }
}
