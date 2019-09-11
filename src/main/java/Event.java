import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    private String format = "E, dd MMM, hh:mm a";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    private Date startDate;
    private Date endDate;

    /**
     * Constructor of Event.
     * @param task String object containing the description of the Deadline task
     * @param startDate Date object denoting the starting date of this Event
     * @param endDate Date object denoting the ending date of this Event
     */
    public Event(String task, Date startDate, Date endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Encodes the Event object in a particular format.
     * @return String object containing the encoded form of the Event object.
     */
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