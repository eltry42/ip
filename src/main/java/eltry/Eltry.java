package eltry;

import java.util.ArrayList;

public class Eltry {

    private Ui ui;
    private Storage storage;
    private Parsers parser;
    private TaskList taskList;

    private static final String LOGO =
        "  _____ _   _____ ______   __\n" +
        " | ____| | |_   _|  _ \\ \\ / /\n" +
        " |  _| | |   | | | |_) \\ V / \n" +
        " | |___| |___| | |  _ < | |  \n" +
        " |_____|_____|_| |_| \\_\\|_|  ";

    public Eltry() {
        this.ui = new Ui();
        this.storage = new Storage("./data/list.txt");
        this.parser = new Parsers();

        ArrayList<Task> loadedTasks;
        try {
            loadedTasks = storage.load();
        } catch (EltryException e) {
            ui.getErrorMessage(e);
            loadedTasks = new ArrayList<>();
        }

        this.taskList = new TaskList(loadedTasks);
    }

    public String getResponse(String input) {
        try {
            Command cmd = parser.parse(input);

            switch (cmd.action) {
                case "bye":
                    return ui.getBye();

                case "list":
                    return ui.getListTasksMessage(taskList.getAll());

                case "mark":
                    taskList.mark(cmd.index);
                    storage.save(taskList.getAll());
                    return ui.getMarkTaskMessage(taskList.get(cmd.index));

                case "unmark":
                    taskList.unmark(cmd.index);
                    storage.save(taskList.getAll());
                    return "Okay, I've marked this task as not done yet:\n" + taskList.get(cmd.index);

                case "delete":
                    Task removed = taskList.remove(cmd.index);
                    storage.save(taskList.getAll());
                    return ui.getDeleteTaskMessage(removed, taskList.getAll());

                case "todo":
                    Todo todo = new Todo(cmd.arg1);
                    taskList.add(todo);
                    storage.save(taskList.getAll());
                    return ui.getAddTaskMessage(todo, taskList.getAll());

                case "deadline":
                    Deadline deadline = new Deadline(cmd.arg1, cmd.arg2);
                    taskList.add(deadline);
                    storage.save(taskList.getAll());
                    return ui.getAddTaskMessage(deadline, taskList.getAll());

                case "event":
                    Event event = new Event(cmd.arg1, cmd.arg2, cmd.arg3);
                    taskList.add(event);
                    storage.save(taskList.getAll());
                    return ui.getAddTaskMessage(event, taskList.getAll());

                case "find":
                    ArrayList<Task> matches = new ArrayList<>();
                    for (Task task : taskList.getAll()) {
                        if (task.description.toLowerCase().equals(cmd.arg1.toLowerCase())) {
                            matches.add(task);
                        }
                    }
                    if (matches.isEmpty()) {
                        return "No tasks found matching: " + cmd.arg1;
                    } else {
                        return ui.getListTasksMessage(matches);
                    }

                default:
                    return "Unknown command: " + cmd.action;
            }
        } catch (EltryException e) {
            return ui.getErrorMessage(e);
        }
    }
}
