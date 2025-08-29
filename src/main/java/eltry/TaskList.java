package eltry;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 * Supports adding, removing, marking/unmarking tasks, and retrieving task information.
 */
public class TaskList {

    /** Internal list that stores the tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index zero-based index of the task
     * @return the task at the given index
     * @throws EltryException if the index is invalid
     */
    public Task get(int index) throws EltryException {
        if (index < 0 || index >= tasks.size()) {
            throw new EltryException("Invalid task number.");
        }
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index zero-based index of the task to remove
     * @return the removed task
     * @throws EltryException if the index is invalid
     */
    public Task remove(int index) throws EltryException {
        Task task = get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index zero-based index of the task to mark
     * @throws EltryException if the index is invalid
     */
    public void mark(int index) throws EltryException {
        get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index zero-based index of the task to unmark
     * @throws EltryException if the index is invalid
     */
    public void unmark(int index) throws EltryException {
        get(index).markAsNotDone();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the internal list of tasks.
     *
     * @return the ArrayList containing all tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }
}
