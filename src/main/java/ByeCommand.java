public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }

    public boolean isExit() {
        return true;
    }
}
