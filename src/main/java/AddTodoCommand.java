import java.io.IOException;

public class AddTodoCommand extends Command {
    private String taskDescription;

    public AddTodoCommand(String description) {
        this.taskDescription = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.addTodo(this.taskDescription);
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
