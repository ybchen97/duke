import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    public void createTaskList() {
        tasks = new TaskList();
        tasks.addTodo("eat lunch later");
    }

    @Test
    public void completeTask_inList_success() throws Exception {
        tasks.complete(1);
    }

    @Test
    public void completeTask_notInList_exceptionThrown() {
        try {
            tasks.complete(100);
        } catch (DukeException e) {
            assertEquals("Task does not exist!", e.getMessage());
        }
    }

    @Test
    public void deleteTask_inList_success() throws Exception {
        tasks.delete(1);
    }

    @Test
    public void deleteTask_notInList_exceptionThrown() {
        try {
            tasks.delete(100);
        } catch (DukeException e) {
            assertEquals("Task does not exist!", e.getMessage());
        }
    }
}
