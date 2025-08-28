package eltry;
// Event.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

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

    @Override
    public String toString() {
        String fromPretty = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")).toLowerCase();
        String toPretty = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")).toLowerCase();
        return "[E]" + super.toString() + " (from: " + fromPretty + " to: " + toPretty + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter) + " | " + to.format(formatter);
    }
}