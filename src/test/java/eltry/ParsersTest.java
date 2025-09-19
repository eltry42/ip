package eltry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Parsers} class.
 * Ensures that commands are correctly parsed and invalid inputs throw {@link EltryException}.
 */
class ParsersTest {

    /**
     * Tests parsing of a valid todo command.
     */
    @Test
    void parseTodo_validDescription_returnsTodoCommand() throws EltryException {
        Command cmd = Parsers.parse("todo read book");
        assertEquals("todo", cmd.action);
        assertEquals("read book", cmd.arg1);
    }

    @Test
    void parseTodo_emptyDescription_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> Parsers.parse("todo"));
        assertTrue(thrown.getMessage().contains("cannot be empty"));
    }

    /**
     * Tests parsing of a valid deadline command.
     */
    @Test
    void parseDeadline_validInput_returnsDeadlineCommand() throws EltryException {
        Command cmd = Parsers.parse("deadline submit report /by 2025-04-05 1830");
        assertEquals("deadline", cmd.action);
        assertEquals("submit report", cmd.arg1);
        assertEquals("2025-04-05 1830", cmd.arg2);
    }

    @Test
    void parseDeadline_missingBy_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> Parsers.parse("deadline submit report"));
        assertTrue(thrown.getMessage().contains("Usage"));
    }

    /**
     * Tests parsing of a valid event command.
     */
    @Test
    void parseEvent_validInput_returnsEventCommand() throws EltryException {
        Command cmd = Parsers.parse("event homework /from 2025-09-01 1800 /to 2025-09-02 1800");
        assertEquals("event", cmd.action);
        assertEquals("homework", cmd.arg1);
        assertEquals("2025-09-01 1800", cmd.arg2);
        assertEquals("2025-09-02 1800", cmd.arg3);
    }

    @Test
    void parseEvent_missingFromOrTo_throwsException() {
        EltryException thrown = assertThrows(EltryException.class,
            () -> Parsers.parse("event homework /from 2025-09-01 1800"));
        assertTrue(thrown.getMessage().contains("Usage"));
    }

    /**
     * Tests parsing of mark, unmark, and delete commands.
     */
    @Test
    void parseMark_validNumber_returnsMarkCommand() throws EltryException {
        Command cmd = Parsers.parse("mark 1");
        assertEquals("mark", cmd.action);
        assertEquals(0, cmd.index);
    }

    @Test
    void parseUnmark_validNumber_returnsUnmarkCommand() throws EltryException {
        Command cmd = Parsers.parse("unmark 2");
        assertEquals("unmark", cmd.action);
        assertEquals(1, cmd.index);
    }

    @Test
    void parseDelete_validNumber_returnsDeleteCommand() throws EltryException {
        Command cmd = Parsers.parse("delete 3");
        assertEquals("delete", cmd.action);
        assertEquals(2, cmd.index);
    }

    @Test
    void parseMark_invalidNumber_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> Parsers.parse("mark abc"));
        assertTrue(thrown.getMessage().contains("Invalid task number"));
    }

    /**
     * Tests parsing of find command.
     */
    @Test
    void parseFind_validKeyword_returnsFindCommand() throws EltryException {
        Command cmd = Parsers.parse("find book");
        assertEquals("find", cmd.action);
        assertEquals("book", cmd.arg1);
    }

    /**
     * Tests parsing of simple commands like list and bye.
     */
    @Test
    void parseList_returnsListCommand() throws EltryException {
        Command cmd = Parsers.parse("list");
        assertEquals("list", cmd.action);
    }

    @Test
    void parseBye_returnsByeCommand() throws EltryException {
        Command cmd = Parsers.parse("bye");
        assertEquals("bye", cmd.action);
    }

    /**
     * Tests invalid or unknown commands.
     */
    @Test
    void parseUnknownCommand_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> Parsers.parse("foobar"));
        assertTrue(thrown.getMessage().contains("Unknown command"));
    }

    /**
     * Tests empty or blank input.
     */
    @Test
    void parseEmptyInput_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> Parsers.parse(""));
        assertTrue(thrown.getMessage().contains("cannot be empty"));
    }

    @Test
    void parseBlankInput_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> Parsers.parse("   "));
        assertTrue(thrown.getMessage().contains("cannot be empty"));
    }
}
