package eltry;
import java.util.ArrayList;

// Eltry.java
public class Eltry {
    private static final String LOGO = 
        "  _____ _   _____ ______   __\n" +
        " | ____| | |_   _|  _ \\ \\ / /\n" +
        " |  _| | |   | | | |_) \\ V / \n" +
        " | |___| |___| | |  _ < | |  \n" +
        " |_____|_____|_| |_| \\_\\|_|  ";

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("../../../data/list.txt");
        Parsers parser = new Parsers();

        ArrayList<Task> loadedTasks;
        try {
            loadedTasks = storage.load();
        } catch (EltryException e) {
            ui.showError(e.getMessage());
            loadedTasks = new ArrayList<>();
        }

        TaskList taskList = new TaskList(loadedTasks);

        ui.showWelcome(LOGO, "Eltry");

        while (true) {
            try {
                String input = ui.readCommand();
                Command cmd = parser.parse(input);

                switch (cmd.action) {
                    case "bye":
                        ui.showGoodbye();
                        ui.close();
                        return;
                    case "list":
                        ui.showTaskList(taskList.getAll());
                        break;
                    case "mark":
                        taskList.mark(cmd.index);
                        storage.save(taskList.getAll());
                        ui.showTaskMarked(taskList.get(cmd.index));
                        break;
                    case "unmark":
                        taskList.unmark(cmd.index);
                        storage.save(taskList.getAll());
                        ui.showTaskUnmarked(taskList.get(cmd.index));
                        break;
                    case "delete":
                        Task removed = taskList.remove(cmd.index);
                        storage.save(taskList.getAll());
                        ui.showTaskDeleted(removed, taskList.size());
                        break;
                    case "todo":
                        Todo todo = new Todo(cmd.arg1);
                        taskList.add(todo);
                        storage.save(taskList.getAll());
                        ui.showTaskAdded(todo, taskList.size());
                        break;
                    case "deadline":
                        try {
                            Deadline deadline = new Deadline(cmd.arg1, cmd.arg2);
                            taskList.add(deadline);
                            storage.save(taskList.getAll());
                            ui.showTaskAdded(deadline, taskList.size());
                        } catch (EltryException e) {
                            ui.showError(e.getMessage());
                        }
                        break;
                    case "event":
                        Event event = new Event(cmd.arg1, cmd.arg2, cmd.arg3);
                        taskList.add(event);
                        storage.save(taskList.getAll());
                        ui.showTaskAdded(event, taskList.size());
                        break;
                    default:
                        ui.showError("Unknown command: " + cmd.action);
                }
            } catch (EltryException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}