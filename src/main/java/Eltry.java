import java.util.Scanner;

class Task {
    private String description;
    private boolean isDone;

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

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

public class Eltry {
    public static void main(String[] args) {
        String chatbotName = "Eltry";
        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;

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
        System.out.println(" Type tasks to add them, 'list' to view tasks, 'mark <num>' to mark done, 'unmark <num>' to undo, and 'bye' to exit!");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine().trim();
            System.out.println("____________________________________________________________");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon! 😊");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println(" No tasks in your list yet.");
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + "." + tasks[i]);
                    }
                }
            } else if (input.toLowerCase().startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < taskCount) {
                        tasks[index].markAsDone();
                        System.out.println(" Nice! I've marked this task as done:\n   " + tasks[index]);
                    } else {
                        System.out.println(" Invalid task number!");
                    }
                } catch (Exception e) {
                    System.out.println(" Usage: mark <task_number>");
                }
            } else if (input.toLowerCase().startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < taskCount) {
                        tasks[index].markAsNotDone();
                        System.out.println(" OK, I've marked this task as not done yet:\n   " + tasks[index]);
                    } else {
                        System.out.println(" Invalid task number!");
                    }
                } catch (Exception e) {
                    System.out.println(" Usage: unmark <task_number>");
                }
            } else {
                if (taskCount < tasks.length) {
                    tasks[taskCount++] = new Task(input);
                    System.out.println(" added: " + input);
                } else {
                    System.out.println(" Cannot add more tasks. Storage full!");
                }
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
