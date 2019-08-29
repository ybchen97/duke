import java.io.IOException;

public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand(int num) {
        this.taskNum = num;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.complete(this.taskNum);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            // todo refactor throwing IOException
            throw new DukeException("Error saving tasks");
        }
        // todo ui showDone
        ui.showDone(t);
    }
}
