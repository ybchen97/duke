import java.io.IOException;

public class AddEventCommand extends Command {

    /** Description of the Event task. */
    private String taskDescription;

    /** String containing the starting date and time information of the event. */
    private String startDateTime;

    /** String containing the ending date and time information of the event. */
    private String endDateTime;

    /**
     * Constructor for AddEventCommand.
     * @param description String object containing the description of the Event task to be added.
     * @param startDateTime String object containing the starting date and time information of the event.
     * @param endDateTime String object containing the ending date and time information of the event.
     */
    public AddEventCommand(String description, String startDateTime, String endDateTime) {
        this.taskDescription = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Executes the steps to add a Event task to the TaskList of Duke.
     * @param tasks TaskList object of Duke that stores the list of current Task objects.
     * @param ui Ui object of Duke that is responsible for displaying responses for user input.
     * @param storage Storage object of Duke that is responsible for encoding and decoding tasks to and from a data
     *                file.
     * @return String object that contains the response from Ui object.
     * @throws DukeException Exception is thrown when an error in saving to the data file occurs.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.addEvent(this.taskDescription, this.startDateTime, this.endDateTime);
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
