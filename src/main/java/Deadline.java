import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Deadline extends Task {

    private String encodeFormat = "dd-MM-yyyy HH:mm";
    private String displayFormat = "E, dd MMM, hh:mm a";
    private SimpleDateFormat displayDateFormat = new SimpleDateFormat(displayFormat);
    private SimpleDateFormat encodeDateFormat = new SimpleDateFormat(encodeFormat);
    private Date by;

    /**
     * Constructor of Deadline.
     * @param task String object containing the description of the Deadline task.
     * @param date String object denoting the deadline of this task.
     */
    public Deadline(String task, String date) {
        super(task);
        SimpleDateFormat parseDateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.by = parseDateFormat.parse(date, new ParsePosition(0));
    }

    /**
     * Constructor of Deadline.
     * @param task String object containing the description of the Deadline task.
     * @param date Date object denoting the deadline of this task.
     */
    public Deadline(String task, Date date) {
        super(task);
        this.by = date;
    }

    /**
     * Encodes the Deadline object in a particular format.
     * @return String object containing the encoded form of the Deadline object.
     */
    public String encode() {
        System.out.println(this.by);
        return String.format(
                "D|%d|%s|%s\n",
                super.isDone ? 1 : 0,
                super.description,
                this.encodeDateFormat.format(this.by));
    }

    /**
     * Snoozes the Deadline by a certain time unit specified by the user.
     * @param length Integer denoting the length of unit time to snooze by
     * @param unit String object denoting the unit of time to snooze by
     * @throws DukeException Thrown when the wrong unit is used
     */
    public void snooze(int length, String unit) throws DukeException {
        // Convert Date object to Calendar object
        Calendar c = Calendar.getInstance();
        c.setTime(this.by);

        switch (unit) {
        case "m":
            c.add(Calendar.MINUTE, length);
            break;
        case "h":
            c.add(Calendar.HOUR, length);
            break;
        case "d":
            c.add(Calendar.DATE, length);
            break;
        case "w":
            c.add(Calendar.WEEK_OF_YEAR, length);
            break;
        case "M":
            c.add(Calendar.MONTH, length);
            break;
        default:
            throw new DukeException("You've used the wrong unit! Use one of the following below:\n"
                    + "<m> for minutes\n"
                    + "<h> for hours\n"
                    + "<d> for days\n"
                    + "<w> for weeks\n"
                    + "<M> for months\n");
        }

        // Convert Calendar object back into a Date object
        this.by = c.getTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.displayDateFormat.format(this.by) + ")";
    }

}