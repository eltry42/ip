package eltry;

import java.util.ArrayList;

/**
 * Main application class for Eltry.
 * Handles initialization of UI, Storage, and TaskList, and processes user input.
 */
public class Eltry {

    /** The user interface handler. */
    private final Ui ui;

    /** The storage handler for saving and loading tasks. */
    private final Storage storage;

    /** The list of tasks currently loaded in the application. */
    private final TaskList tasks;

    /**
     * Constructs an Eltry application instance.
     * Initializes UI, Storage, and loads existing tasks from file.
     */
    public Eltry() {
        this.ui = new Ui();
        this.storage = new Storage("./data/list.txt");

        ArrayList<Task> loaded;
        try { loaded = storage.load(); }
        catch (EltryException e) { loaded = new ArrayList<>(); }

        this.tasks = new TaskList(loaded);
    }

    /**
     * Processes user input by parsing it into a Command and executing it.
     *
     * @param input the raw user input string
     * @return the response message after executing the command
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parsers.parse(input);
            return cmd.execute(tasks, ui, storage);
        } catch (EltryException e) {
            return ui.showError(e.getMessage());
        }
    }
}
