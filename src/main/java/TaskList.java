import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskList {

    /** ArrayList that stores all the Task object that the bot currently has. */
    private ArrayList<Task> tasks;

    /** Constructs TaskList object. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs TaskList object with an ArrayList of existing Task objects.
     * @param tasks ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Creates a Todo object and adds it into the TaskList.
     * @param taskDescription Description of the Todo task.
     * @return Returns the Todo object that was added in the TaskList.
     */
    public Task addTodo(String taskDescription) {
        Task t = new Todo(taskDescription);
        this.tasks.add(t);
        return t;
    }

    /**
     * Creates a Deadline object and adds it into the TaskList.
     * @param taskDescription Description of the Deadline task.
     * @param dateStr String denoting the due date of the Deadline task.
     * @return Returns the Deadline object that was added in the TaskList.
     */
    public Task addDeadline(String taskDescription, String dateStr) {
        Task t = new Deadline(taskDescription, dateStr);
        this.tasks.add(t);
        return t;
    }

    /**
     * Creates a Event object and adds it into the TaskList.
     * @param taskDescription Description of the Event task.
     * @param startDateTimeStr String denoting the start datetime of the Event task.
     * @param endDateTimeStr String denoting the end datetime of the Event task.
     * @return Returns the Event object that was added in the TaskList.
     */
    public Task addEvent(String taskDescription, String startDateTimeStr, String endDateTimeStr) {
        Task t = new Event(taskDescription, startDateTimeStr, endDateTimeStr);
        this.tasks.add(t);
        return t;
    }

    /**
     * Completes a specified task in the TaskList.
     * @param num int specifying the task number of the task to completed.
     * @return Task object that is completed.
     * @throws DukeException An exception is thrown if the task specified does not exist.
     */
    public Task complete(int num) throws DukeException {
        Task t;
        try {
            t = this.tasks.get(num - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist!");
        }
        t.complete();
        return t;
    }

    /**
     * Deletes a specified task in the TaskList.
     * @param num int specifying the task number of the task to deleted.
     * @return Task object that is deleted.
     * @throws DukeException An exception is thrown if the task specified does not exist.
     */
    public Task delete(int num) throws DukeException {
        Task t;
        try {
            t = this.tasks.remove(num - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist!");
        }
        return t;
    }

    /**
     * Finds the tasks that has the keyword given by the user present in their task description.
     * @param word String denoting the keyword.
     * @return ArrayList of Task objects that have the keyword in their task description.
     */
    public ArrayList<Task> find(String word) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.contains(word)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }

    /**
     * Snoozes the chosen task by a certain length of time specified by the user.
     * @param taskNum Integer denoting the index corresponding to the Task object in the list of Task objects
     * @param length Integer denoting the length of unit time to snooze by
     * @param unit String object denoting the unit of time to snooze by
     * @return Task object that is snoozed
     * @throws DukeException Thrown if the task specified does not exist, or when the wrong unit is used
     */
    public Task snooze(int taskNum, int length, String unit) throws DukeException {
        Task t;
        try {
            t = this.tasks.get(taskNum);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist!");
        }
        t.snooze(length, unit);
        return t;
    }

    /**
     * Getter that gets the ArrayList of Task objects in a newly constructed ArrayList.
     * @return ArrayList of Task objects present in the bot.
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(this.tasks);
    }

}
