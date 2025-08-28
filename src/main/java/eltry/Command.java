package eltry;
// Command.java
public class Command {
    public final String action;
    public final int index;
    public final String arg1;
    public final String arg2;
    public final String arg3;

    public Command(String action) {
        this(action, -1, null, null, null);
    }

    public Command(String action, int index) {
        this(action, index, null, null, null);
    }

    public Command(String action, String arg1) {
        this(action, -1, arg1, null, null);
    }

    public Command(String action, String arg1, String arg2) {
        this(action, -1, arg1, arg2, null);
    }

    public Command(String action, String arg1, String arg2, String arg3) {
        this(action, -1, arg1, arg2, arg3);
    }

    private Command(String action, int index, String arg1, String arg2, String arg3) {
        this.action = action;
        this.index = index;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }
}