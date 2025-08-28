// TaskList.java
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) throws EltryException {
        if (index < 0 || index >= tasks.size()) {
            throw new EltryException("Invalid task number.");
        }
        return tasks.get(index);
    }

    public Task remove(int index) throws EltryException {
        Task task = get(index);
        tasks.remove(index);
        return task;
    }

    public void mark(int index) throws EltryException {
        get(index).markAsDone();
    }

    public void unmark(int index) throws EltryException {
        get(index).markAsNotDone();
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }
}