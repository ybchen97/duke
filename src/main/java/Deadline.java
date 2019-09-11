import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private String format = "E, dd MMM, hh:mm a";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    private Date by;

    /**
     * Constructor of Deadline.
     * @param task String object containing the description of the Deadline task.
     * @param by Date object denoting the deadline of this task.
     */
    public Deadline(String task, Date by) {
        super(task);
        this.by = by;
    }

    /**
     * Encodes the Deadline object in a particular format.
     * @return String object containing the encoded form of the Deadline object.
     */
    public String encode() {
        return String.format(
                "D|%d|%s|%s\n",
                super.isDone ? 1 : 0,
                super.description,
                this.dateFormat.format(this.by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateFormat.format(this.by) + ")";
    }

}