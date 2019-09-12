import java.io.IOException;

public class SnoozeCommand extends Command {
    /** Index number of the Task object in the current list of Task objects. */
    private int taskNum;
    private int length;
    private String unit;

    /**
     * Constructor for SnoozeCommand.
     * @param taskNum Integer that corresponds to the index of the Task object in the current list of Task objects.
     * @param length Length of a unit time to snooze by
     * @param unit String that denotes the unit of time to snooze by (m: mins, h: hrs, d: days, w: weeks, M: months)
     */
    public SnoozeCommand(int taskNum, int length, String unit) {
        this.taskNum = taskNum;
        this.length = length;
        this.unit = unit;
    }

    /**
     * Executes the steps to snooze a Task object.
     * @param tasks TaskList object of Duke that stores the list of current Task objects.
     * @param ui Ui object of Duke that is responsible for displaying responses for user input.
     * @param storage Storage object of Duke that is responsible for encoding and decoding tasks to and from a data
     *                file.
     * @return String object that contains the response from Ui object.
     * @throws DukeException Exception is thrown when an error in saving to the data file occurs, or when the task
     *                number exceeds the length of the current list of Tasks, or when the wrong unit is used.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.snooze(this.taskNum, this.length, this.unit);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            // todo refactor throwing IOException
            throw new DukeException("Error saving tasks");
        }
        return ui.showSnooze(t);
    }
}
