public class IllegalCommand extends Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // todo ui showIllegalCommand
        return ui.showIllegalCommand();
    }
}
