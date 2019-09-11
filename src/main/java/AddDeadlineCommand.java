import java.io.IOException;

public class AddDeadlineCommand extends Command {

    /** Description of the deadline task. */
    private String taskDescription;

    /** Date information in String form. */
    private String date;

    /**
     * Constructor for AddDeadlineCommand.
     * @param description String input that denotes the description of the deadline task
     * @param date String input of date information of the deadline task
     */
    public AddDeadlineCommand(String description, String date) {
        this.taskDescription = description;
        this.date = date;
    }

    /**
     * Executes the steps to add a Deadline task to the TaskList of Duke.
     * @param tasks TaskList object of Duke that stores the list of current Task objects.
     * @param ui Ui object of Duke that is responsible for displaying responses for user input.
     * @param storage Storage object of Duke that is responsible for encoding and decoding tasks to and from a data
     *                file.
     * @return String object that contains the response from Ui object.
     * @throws DukeException Exception is thrown when an error in saving to the data file occurs.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.addDeadline(this.taskDescription, this.date);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            // todo refactor throwing IOException
            throw new DukeException("Error saving tasks");
        }
        // todo ui showAddTask
        return ui.showAddTask(t);
    }
}
