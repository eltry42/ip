package eltry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Extends the Task class and adds a due date/time field.
 */
public class Deadline extends Task {

    /** The due date and time of the task. */
    protected LocalDateTime by;

    /** Formatter for parsing and formatting date strings. */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Creates a new Deadline task with the specified description and due date/time string.
     *
     * @param description description of the task
     * @param byStr due date/time string in the format yyyy-MM-dd HHmm
     * @throws EltryException if the provided date string cannot be parsed
     */
    public Deadline(String description, String byStr) throws EltryException {
        super(description);
        try {
            this.by = LocalDateTime.parse(byStr, formatter);
        } catch (DateTimeParseException e) {
            throw new EltryException(
                "Invalid date format. Use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)."
            );
        }
    }

    /**
     * Returns a human-readable string representation of the Deadline task.
     *
     * @return formatted string including task type, description, and due date/time
     */
    @Override
    public String toString() {
        String pretty = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")).toLowerCase();
        return "[D]" + super.toString() + " (by: " + pretty + ")";
    }

    /**
     * Returns a string suitable for saving to a file.
     *
     * @return formatted string for file storage
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}
