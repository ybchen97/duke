public class IllegalCommand extends Command {
    /**
     * Executes and returns response when user gives an illegal command.
     * @param tasks TaskList object of Duke that stores the list of current Task objects.
     * @param ui Ui object of Duke that is responsible for displaying responses for user input.
     * @param storage Storage object of Duke that is responsible for encoding and decoding tasks to and from a data
     *                file.
     * @return String object that contains the response from Ui object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // todo ui showIllegalCommand
        return ui.showIllegalCommand();
    }
}
