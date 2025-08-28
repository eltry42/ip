import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.*;
class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        file.getParentFile().mkdirs(); // ensure ./data exists

        if (!file.exists()) return tasks; // first run, return empty

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");

                Task task;
                switch (type) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        continue;
                }

                if (isDone) task.markAsDone();
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return tasks;
    }

    public void save(List<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
class EltryException extends Exception {
    public EltryException(String message) {
        super(message);
    }
}

abstract class Task {
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

    public abstract String toFileString();
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
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

        @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
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

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}

public class Eltry {
    public static void main(String[] args) {
        String chatbotName = "Eltry";
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("../../../data/list.txt");
        ArrayList<Task> tasks = storage.load();  

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
                    if (tasks.isEmpty()) throw new EltryException("Your task list is empty.");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                } else if (input.toLowerCase().startsWith("mark ")) {
                    int index = parseIndex(input, tasks.size());
                    tasks.get(index).markAsDone();
                    storage.save(tasks); 
                    System.out.println(" Nice! I've marked this task as done:\n   " + tasks.get(index));
                } else if (input.toLowerCase().startsWith("unmark ")) {
                    int index = parseIndex(input, tasks.size());
                    tasks.get(index).markAsNotDone();
                    storage.save(tasks); 
                    System.out.println(" OK, I've marked this task as not done yet:\n   " + tasks.get(index));
                } else if (input.toLowerCase().startsWith("delete ")) {
                    int index = parseIndex(input, tasks.size());
                    Task removed = tasks.remove(index);
                    storage.save(tasks); 
                    System.out.println(" Noted. I've removed this task:\n   " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.toLowerCase().startsWith("todo")) {
                    String desc = input.substring(4).trim();
                    if (desc.isEmpty()) throw new EltryException("The description of a todo cannot be empty.");
                    tasks.add(new Todo(desc));
                    storage.save(tasks); 
                    System.out.println(" Got it. I've added this task:\n   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.toLowerCase().startsWith("deadline")) {
                    String[] parts = input.substring(8).split("/by");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new EltryException("Usage: deadline <desc> /by <date>");
                    }
                    tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
                    storage.save(tasks); 
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
                    storage.save(tasks); 
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
