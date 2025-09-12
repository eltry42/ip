package eltry;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles loading and saving of tasks to a file.
 * Responsible for persisting task data between program runs.
 */
public class Storage {

    /** File path where tasks are stored. */
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath path to the file where tasks will be saved/loaded
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return a list of tasks loaded from the file
     * @throws EltryException if there is an error reading the file
     */
    public ArrayList<Task> load() throws EltryException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null) parent.mkdirs();

        if (!file.exists()) return tasks;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) continue;

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                Task task;

                switch (type) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        try {
                            task = new Deadline(parts[2], parts[3]);
                        } catch (EltryException e) {
                            continue;
                        }
                        break;
                    case "E":
                        if (parts.length < 5) continue;
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        continue;
                }

                if (isDone) task.markAsDone();
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new EltryException("Error loading file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks the list of tasks to save
     * @throws EltryException if there is an error writing to the file
     */
    public void save(TaskList tasks) throws EltryException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks.getAll()) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new EltryException("Error saving file: " + e.getMessage());
        }
    }
}
