import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private String taskDescription;
    private String date;

    public AddDeadlineCommand(String description, String date) {
        this.taskDescription = description;
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.addDeadline(this.taskDescription, this.date);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            // todo refactor throwing IOException
            throw new DukeException("Error saving tasks");
        }
        // todo ui showAddTask
        ui.showAddTask(t);
    }
}
