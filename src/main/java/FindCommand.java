import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        // Check if keyword contains only 1 word
        assert(keyword.split(" ").length == 1);
        this.keyword = keyword;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(this.keyword);
        return ui.showFindTask(foundTasks);
    }
}
