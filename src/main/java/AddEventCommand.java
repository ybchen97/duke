import java.io.IOException;

public class AddEventCommand extends Command {
    private String taskDescription;
    private String startDateTime;
    private String endDateTime;

    public AddEventCommand(String description, String startDateTime, String endDateTime) {
        this.taskDescription = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.addEvent(this.taskDescription, this.startDateTime, this.endDateTime);
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
