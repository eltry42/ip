package eltry;

/**
 * Abstract base class representing a generic task.
 * Provides common fields and methods for all task types.
 */
public abstract class Task {

    /** Description of the task. */
    protected String description;

    /** Indicates whether the task is completed. */
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * By default, the task is not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representing the status of the task.
     *
     * @return "X" if done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a human-readable string representation of the task.
     *
     * @return formatted string including status icon and description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string suitable for saving to a file.
     * Must be implemented by subclasses.
     *
     * @return formatted string for file storage
     */
    public abstract String toFileString();
}
