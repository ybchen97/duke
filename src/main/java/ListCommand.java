public class ListCommand extends Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks.getAllTasks());
    }
}
