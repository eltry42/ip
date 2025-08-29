package eltry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a start and end time (an event).
 * Extends the Task class and adds start and end date/time fields.
 */
public class Event extends Task {

    /** Start date and time of the event. */
    protected LocalDateTime from;

    /** End date and time of the event. */
    protected LocalDateTime to;

    /** Formatter for parsing and formatting date strings. */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Creates a new Event task with the specified description, start time, and end time.
     *
     * @param description description of the event
     * @param fromStr start date/time string in the format yyyy-MM-dd HHmm
     * @param toStr end date/time string in the format yyyy-MM-dd HHmm
     * @throws EltryException if either date string cannot be parsed
     */
    public Event(String description, String fromStr, String toStr) throws EltryException {
        super(description);
        try {
            this.from = LocalDateTime.parse(fromStr, formatter);
        } catch (DateTimeParseException e) {
            throw new EltryException("Invalid 'from' date format. Use yyyy-MM-dd HHmm.");
        }
        try {
            this.to = LocalDateTime.parse(toStr, formatter);
        } catch (DateTimeParseException e) {
            throw new EltryException("Invalid 'to' date format. Use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Returns a human-readable string representation of the Event task.
     *
     * @return formatted string including task type, description, and start/end date/time
     */
    @Override
    public String toString() {
        String fromPretty = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")).toLowerCase();
        String toPretty = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")).toLowerCase();
        return "[E]" + super.toString() + " (from: " + fromPretty + " to: " + toPretty + ")";
    }

    /**
     * Returns a string suitable for saving to a file.
     *
     * @return formatted string for file storage
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " 
                + from.format(formatter) + " | " + to.format(formatter);
    }
}
