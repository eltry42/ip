import java.util.Scanner;

public class Eltry {
    public static void main(String[] args) {
        String chatbotName = "Eltry";
        Scanner scanner = new Scanner(System.in);
        String input = "";

        String logo = """
            _____ _   _____ ______   __
           | ____| | |_   _|  _ \\ \\ / /
           |  _| | |   | | | |_) \\ V / 
           | |___| |___| | |  _ < | |  
           |_____|_____|_| |_| \\_\\|_|            
          """;
          

        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm " + chatbotName + ", your friendly echo bot.");
        System.out.println(" Type anything and I'll echo it back. Type 'bye' to exit!");
        System.out.println("____________________________________________________________");

        while (true) {
            input = scanner.nextLine();

            System.out.println("____________________________________________________________");
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye! Hope to see you again soon! 😊");
                System.out.println("____________________________________________________________");
                break;
            } else {
                System.out.println(" " + input);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
