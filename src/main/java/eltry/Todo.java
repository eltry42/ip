package eltry;

/**
 * Represents a Todo task with a description but no date/time.
 * Inherits common behavior from {@link Task}.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a human-readable string representation of the todo task.
     *
     * @return formatted string including type indicator and status
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string suitable for saving to a file.
     *
     * @return formatted string for file storage
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
