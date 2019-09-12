public class Todo extends Task {

    /**
     * Constructor for Todo class.
     * @param task String object containing the description of the Todo task.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Encodes the Todo object in a particular format.
     * @return String object containing the encoded form of the Todo object.
     */
    public String encode() {
        return String.format(
                "T|%d|%s\n",
                super.isDone ? 1 : 0,
                super.description);
    }

    public void snooze(int length, String unit) throws DukeException {
        throw new DukeException("This task cannot be snoozed!");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}