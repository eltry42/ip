package eltry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Parsers} class.
 * Ensures that commands are correctly parsed and invalid inputs throw {@link EltryException}.
 */
class ParsersTest {

    private final Parsers parser = new Parsers();

    /**
     * Tests that parsing a valid todo command returns a Command with the correct action and description.
     */
    @Test
    void parseTodo_validDescription_returnsTodoCommand() throws EltryException {
        Command cmd = parser.parse("todo read book");

        assertEquals("todo", cmd.action);
        assertEquals("read book", cmd.arg1);
    }

    /**
     * Tests that parsing a todo command with an empty description throws {@link EltryException}.
     */
    @Test
    void parseTodo_emptyDescription_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> parser.parse("todo"));

        assertTrue(thrown.getMessage().contains("cannot be empty"));
    }

    /**
     * Tests that parsing a valid deadline command returns a Command with the correct action,
     * description, and deadline.
     */
    @Test
    void parseDeadline_validInput_returnsDeadlineCommand() throws EltryException {
        Command cmd = parser.parse("deadline submit report /by 2025-04-05 1830");

        assertEquals("deadline", cmd.action);
        assertEquals("submit report", cmd.arg1);
        assertEquals("2025-04-05 1830", cmd.arg2);
    }

    /**
     * Tests that parsing a deadline command without a '/by' throws {@link EltryException}.
     */
    @Test
    void parseDeadline_missingBy_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> parser.parse("deadline submit report"));

        assertTrue(thrown.getMessage().contains("Usage"));
    }

    /**
     * Tests that parsing a valid mark command returns a Command with the correct action and index.
     */
    @Test
    void parseMark_validNumber_returnsMarkCommand() throws EltryException {
        Command cmd = parser.parse("mark 1");

        assertEquals("mark", cmd.action);
        assertEquals(0, cmd.index);
    }

    /**
     * Tests that parsing a mark command with an invalid number throws {@link EltryException}.
     */
    @Test
    void parseMark_invalidNumber_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> parser.parse("mark abc"));

        assertTrue(thrown.getMessage().contains("Usage"));
    }
}
