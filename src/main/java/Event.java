import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Event extends Task {

    private String encodeFormat = "dd-MM-yyyy HH:mm";
    private String displayFormat = "E, dd MMM, hh:mm a";
    private SimpleDateFormat displayDateFormat = new SimpleDateFormat(displayFormat);
    private SimpleDateFormat encodeDateFormat = new SimpleDateFormat(encodeFormat);
    private Date startDate;
    private Date endDate;

    /**
     * Constructor of Event.
     * @param task String object containing the description of the Deadline task
     * @param startDate String object denoting the starting date of this Event
     * @param endDate String object denoting the ending date of this Event
     */
    public Event(String task, String startDate, String endDate) {
        super(task);
        this.startDate = encodeDateFormat.parse(startDate, new ParsePosition(0));
        this.endDate = encodeDateFormat.parse(endDate, new ParsePosition(0));
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
                this.encodeDateFormat.format(this.startDate),
                this.encodeDateFormat.format(this.endDate));
    }

    /**
     * Snoozes the Event by a certain unit time specified by the user.
     * @param length Integer denoting the length of unit time to snooze by
     * @param unit String object denoting the unit of time to snooze by
     * @throws DukeException Thrown when the wrong unit is used
     */
    public void snooze(int length, String unit) throws DukeException {
        // Convert Date object to Calendar object
        Calendar startC = Calendar.getInstance();
        Calendar endC = Calendar.getInstance();
        startC.setTime(this.startDate);
        endC.setTime(this.endDate);

        switch (unit) {
        case "m":
            startC.add(Calendar.MINUTE, length);
            endC.add(Calendar.MINUTE, length);
            break;
        case "h":
            startC.add(Calendar.HOUR, length);
            endC.add(Calendar.HOUR, length);
            break;
        case "d":
            startC.add(Calendar.DATE, length);
            endC.add(Calendar.DATE, length);
            break;
        case "w":
            startC.add(Calendar.WEEK_OF_YEAR, length);
            endC.add(Calendar.WEEK_OF_YEAR, length);
            break;
        case "M":
            startC.add(Calendar.MONTH, length);
            endC.add(Calendar.MONTH, length);
            break;
        default:
            throw new DukeException("You've used the wrong unit! Use one of the following below:\n"
                    + "<m> for minutes\n"
                    + "<h> for hours\n"
                    + "<d> for days\n"
                    + "<w> for weeks\n"
                    + "<M> for months\n");
        }

        // Converting Calendar object back into Date object
        this.startDate = startC.getTime();
        this.endDate = endC.getTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.displayDateFormat.format(this.startDate) + " to "
                + this.displayDateFormat.format(this.endDate) + ")";
    }

}