public class ByeCommand extends Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFarewell();
    }

    public boolean isExit() {
        return true;
    }
}
