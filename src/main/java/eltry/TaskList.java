package eltry;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 * Handles adding, deleting, marking/unmarking, finding, and conflict checking.
 */
public class TaskList {

    /** Internal list storing all tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an initial set of tasks.
     *
     * @param tasks initial tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     * Throws EltryException if there is a schedule conflict.
     *
     * @param task task to add
     * @throws EltryException if a scheduling conflict is detected
     */
    public void add(Task task) throws EltryException {
        if (hasConflict(task)) {
            throw new EltryException("Schedule conflict detected! Task not added.");
        }
        tasks.add(task);
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index position of the task
     * @return the task at the specified index
     * @throws EltryException if index is invalid
     */
    public Task get(int index) throws EltryException {
        if (index < 0 || index >= tasks.size()) throw new EltryException("Invalid task number.");
        return tasks.get(index);
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index index of the task to remove
     * @return the deleted task
     * @throws EltryException if index is invalid
     */
    public Task delete(int index) throws EltryException {
        Task t = get(index);
        tasks.remove(index);
        return t;
    }

    /**
     * Marks a task as done.
     *
     * @param index index of the task
     * @return the marked task
     * @throws EltryException if index is invalid
     */
    public Task mark(int index) throws EltryException {
        Task t = get(index);
        t.markAsDone();
        return t;
    }

    /**
     * Marks a task as not done.
     *
     * @param index index of the task
     * @return the unmarked task
     * @throws EltryException if index is invalid
     */
    public Task unmark(int index) throws EltryException {
        Task t = get(index);
        t.markAsNotDone();
        return t;
    }

    /**
     * Finds tasks containing a keyword in their description.
     *
     * @param keyword keyword to search for
     * @return list of matching tasks
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> found = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.toLowerCase().contains(keyword.toLowerCase())) found.add(t);
        }
        return found;
    }

    /**
     * Checks if adding a new task would conflict with existing events.
     *
     * @param newTask the task to check
     * @return true if a conflict exists, false otherwise
     */
    private boolean hasConflict(Task newTask) {
        if (!(newTask instanceof Event)) return false;

        Event newEvent = (Event) newTask;
        for (Task task : tasks) {
            if (task instanceof Event) {
                Event e = (Event) task;
                boolean overlap = !newEvent.getTo().isBefore(e.getFrom()) && !newEvent.getFrom().isAfter(e.getTo());
                if (overlap) return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the list.
     *
     * @return list of all tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }
}
