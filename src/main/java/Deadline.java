import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String task, Date by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.toString() + ")";
    }

}