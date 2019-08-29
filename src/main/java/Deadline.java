import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private String format = "dd-mm-yyyy HH:mm";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    private Date by;

    public Deadline(String task, Date by) {
        super(task);
        this.by = by;
    }

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