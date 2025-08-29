package eltry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    @Test
    public void testToString_validDeadline_showsFormattedDate() {
        // Arrange
        LocalDateTime by = LocalDateTime.of(2025, 4, 5, 18, 30);
        String input = "2025-04-05 1830";
        try {
            Deadline deadline = new Deadline("Submit report", input);

            // Act
            String output = deadline.toString();

            // Assert
            assertTrue(output.contains("Submit report"));
            assertTrue(output.contains("(by: apr 05 2025, 6:30pm)")); 
        } catch (EltryException e) {
            fail("Should not throw exception for valid input");
        }
    }

    @Test
    public void testInvalidDate_throwsException() {
        // Act & Assert
        EltryException thrown = assertThrows(EltryException.class, () -> {
            new Deadline("Submit report", "invalid-date");
        });

        assertTrue(thrown.getMessage().contains("Invalid date format"));
    }
}