package eltry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParsersTest {

    private final Parsers parser = new Parsers();

    @Test
    void parseTodo_validDescription_returnsTodoCommand() throws EltryException {
        Command cmd = parser.parse("todo read book");

        assertEquals("todo", cmd.action);
        assertEquals("read book", cmd.arg1);
    }

    @Test
    void parseTodo_emptyDescription_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> {
            parser.parse("todo");
        });

        assertTrue(thrown.getMessage().contains("cannot be empty"));
    }

    @Test
    void parseDeadline_validInput_returnsDeadlineCommand() throws EltryException {
        Command cmd = parser.parse("deadline submit report /by 2025-04-05 1830");

        assertEquals("deadline", cmd.action);
        assertEquals("submit report", cmd.arg1);
        assertEquals("2025-04-05 1830", cmd.arg2);
    }

    @Test
    void parseDeadline_missingBy_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> {
            parser.parse("deadline submit report");
        });

        assertTrue(thrown.getMessage().contains("Usage"));
    }

    @Test
    void parseMark_validNumber_returnsMarkCommand() throws EltryException {
        Command cmd = parser.parse("mark 1");

        assertEquals("mark", cmd.action);
        assertEquals(0, cmd.index); 
    }

    @Test
    void parseMark_invalidNumber_throwsException() {
        EltryException thrown = assertThrows(EltryException.class, () -> {
            parser.parse("mark abc");
        });

        assertTrue(thrown.getMessage().contains("Usage"));
    }
}