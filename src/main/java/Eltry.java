import java.util.Scanner;

public class Eltry {
    public static void main(String[] args) {
        String chatbotName = "Eltry";
        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[100]; 
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
        System.out.println(" Type anything to store it, 'list' to view all tasks, and 'bye' to exit!");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();

            System.out.println("____________________________________________________________");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon! 😊");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println(" No tasks stored yet.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks[i]);
                    }
                }
            } else {
                if (taskCount < tasks.length) {
                    tasks[taskCount++] = input;
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
