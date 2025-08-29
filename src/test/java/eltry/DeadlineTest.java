package eltry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

/**
 * Unit tests for the {@link Deadline} class.
 * Ensures that deadlines are correctly formatted and invalid inputs throw exceptions.
 */
public class DeadlineTest {

    /**
     * Tests that a valid Deadline object correctly formats its string representation.
     * The formatted string should include the description and a properly formatted date.
     */
    @Test
    public void testToString_validDeadline_showsFormattedDate() {
        // Arrange
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

    /**
     * Tests that creating a Deadline with an invalid date string
     * throws an {@link EltryException}.
     */
    @Test
    public void testInvalidDate_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> {
            new Deadline("Submit report", "invalid-date");
        });

        assertTrue(thrown.getMessage().contains("Invalid date format"));
    }
}
