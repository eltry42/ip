// Deadline.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(String description, String byStr) throws EltryException {
        super(description);
        try {
            this.by = LocalDateTime.parse(byStr, formatter);
        } catch (DateTimeParseException e) {
            throw new EltryException("Invalid date format. Use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800).");
        }
    }

    @Override
    public String toString() {
        String pretty = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")).toLowerCase();
        return "[D]" + super.toString() + " (by: " + pretty + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}