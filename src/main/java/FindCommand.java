import java.util.ArrayList;

public class FindCommand extends Command {
    /** The keyword that the user wants to search for within Duke's list of tasks. */
    private String keyword;

    /**
     * Constructor for FindCommand.
     * @param keyword String object that contains the keyword that the user inputs.
     */
    public FindCommand(String keyword) {
        // Check if keyword contains only 1 word
        assert (keyword.split(" ").length == 1);
        this.keyword = keyword;
    }

    /**
     * Executes the steps to find the list of tasks containing the keyword that the user gives as input.
     * @param tasks TaskList object of Duke that stores the list of current Task objects.
     * @param ui Ui object of Duke that is responsible for displaying responses for user input.
     * @param storage Storage object of Duke that is responsible for encoding and decoding tasks to and from a data
     *                file.
     * @return String object that contains the response from Ui object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(this.keyword);
        return ui.showFindTask(foundTasks);
    }
}
