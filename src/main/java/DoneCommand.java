import java.io.IOException;

public class DoneCommand extends Command {
    /** Index number of the Task object in the current list of Task objects. */
    private int taskNum;

    /**
     * Constructor for DoneCommand.
     * @param num Integer that corresponds to the index of the Task object in the current list of Task objects.
     */
    public DoneCommand(int num) {
        this.taskNum = num;
    }


    /**
     * Executes the steps to mark a Task object as completed.
     * @param tasks TaskList object of Duke that stores the list of current Task objects.
     * @param ui Ui object of Duke that is responsible for displaying responses for user input.
     * @param storage Storage object of Duke that is responsible for encoding and decoding tasks to and from a data
     *                file.
     * @return String object that contains the response from Ui object.
     * @throws DukeException Exception is thrown when an error in saving to the data file occurs, or when the task
     *                number exceeds the length of the current list of Tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.complete(this.taskNum);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            // todo refactor throwing IOException
            throw new DukeException("Error saving tasks");
        }
        return ui.showDone(t);
    }
}
