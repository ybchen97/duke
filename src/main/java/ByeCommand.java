public class ByeCommand extends Command {

    /**
     * Executes the steps to add a Deadline task to the TaskList of Duke.
     * @param tasks TaskList object of Duke that stores the list of current Task objects.
     * @param ui Ui object of Duke that is responsible for displaying responses for user input.
     * @param storage Storage object of Duke that is responsible for encoding and decoding tasks to and from a data
     *                file.
     * @return String object that contains the response from Ui object.
     * @throws DukeException Exception is thrown when an error in saving to the data file occurs.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFarewell();
    }

    public boolean isExit() {
        return true;
    }
}
