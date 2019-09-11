public class ListCommand extends Command {
    /**
     * Executes the steps to display the current list of Task objects that Duke keeps track of.
     * @param tasks TaskList object of Duke that stores the list of current Task objects.
     * @param ui Ui object of Duke that is responsible for displaying responses for user input.
     * @param storage Storage object of Duke that is responsible for encoding and decoding tasks to and from a data
     *                file.
     * @return String object that contains the response from Ui object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks.getAllTasks());
    }
}
