import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public Task addTodo(String taskDescription) {
        Task t = new Todo(taskDescription);
        this.tasks.add(t);
        return t;
    }

    public Task addDeadline(String taskDescription, String dateStr) {
        // Parsing date string into date object
        String format = "dd/mm/yyyy HHmm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = dateFormat.parse(dateStr, new ParsePosition(0));

        Task t = new Deadline(taskDescription, date);
        this.tasks.add(t);
        return t;
    }

    public Task addEvent(String taskDescription, String startDateTimeStr, String endDateTimeStr) {
        // Parsing date string into date object
        String format = "dd/mm/yyyy HHmm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date startDateTime = dateFormat.parse(startDateTimeStr, new ParsePosition(0));
        Date endDateTime = dateFormat.parse(endDateTimeStr, new ParsePosition(0));

        Task t = new Event(taskDescription, startDateTime, endDateTime);
        this.tasks.add(t);
        return t;
    }

    public Task complete(int num) throws DukeException {
        Task t;
        try {
            t = this.tasks.get(num - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist");
        }
        t.complete();
        return t;
    }

    public Task delete(int num) throws DukeException {
        Task t;
        try {
            t = this.tasks.remove(num - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist");
        }
        return t;
    }

    public ArrayList<Task> find(String word) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.contains(word)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(this.tasks);
    }

}
