import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int num) {
        this.taskNum = num;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.delete(this.taskNum);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            // todo refactor throwing IOException
            throw new DukeException("Error saving tasks");
        }
        return ui.showDelete(t);
    }
}
