import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    private String format = "dd-mm-yyyy HH:mm";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    private Date startDate;
    private Date endDate;

    public Event(String task, Date startDate, Date endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String encode() {
        return String.format(
                "E|%d|%s|%s|%s\n",
                super.isDone ? 1 : 0,
                super.description,
                this.dateFormat.format(this.startDate),
                this.dateFormat.format(this.endDate));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.dateFormat.format(this.startDate) + " to "
                + this.dateFormat.format(this.endDate) + ")";
    }

}