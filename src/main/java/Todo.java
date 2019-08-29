public class Todo extends Task {

    public Todo(String task) {
        super(task);
    }

    public String encode() {
        return String.format(
                "T|%d|%s",
                super.isDone ? 1 : 0,
                super.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}