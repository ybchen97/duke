import java.util.ArrayList;
import java.util.StringJoiner;

public class Ui {

    /**
     * Prints the welcome message.
     */
    public String showWelcome() {
        StringJoiner sj = new StringJoiner("\n", "", "\n");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        sj.add("Hello from\n" + logo);
        sj.add("Hello! I'm Bobo_bot\nWhat can I do for you?");
        return sj.toString();
    }

    /**
     * Prints the farewell message.
     */
    public String showFarewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out the task that has been just added by the user.
     * @param t Task object that has been added by the user.
     */
    public String showAddTask(Task t) {
        StringJoiner sj = new StringJoiner("\n", "", "\n");
        sj.add("Got it. I've added this task:");
        sj.add(t.toString());
        return sj.toString();
    }

    /**
     * Prints out the task that has been completed by the user.
     * @param t Task object that has been completed by the user.
     */
    public String showDone(Task t) {
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    /**
     * Prints out the task that has been deleted by the user.
     * @param t Task object that has been deleted by the user.
     */
    public String showDelete(Task t) {
        return "Noted. I've removed this task:\n" + t;
    }

    /**
     * Prints out the current list of tasks that the bot contains.
     * @param tasks An ArrayList of Task objects. Denotes the current list of tasks that the bot contains.
     */
    public String showList(ArrayList<Task> tasks) {
        int size = tasks.size();
        if (size == 0) {
            return "You have no tasks currently.";
        } else {
            StringJoiner sj = new StringJoiner("\n", "", "\n");
            sj.add("Here are your tasks in your list:");
            for (int i = 0; i < size; i++) {
                sj.add(String.format(
                        "%d. %s",
                        i + 1,
                        tasks.get(i)));
            }
            return sj.toString();
        }
    }

    /**
     * Prints out the tasks in the list of tasks that match the keyword given by the user.
     * @param foundTasks ArrayList of Task objects that match the keyword given by the user.
     */
    public String showFindTask(ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            return "Sorry I can't seem to find this task in my list :(";
        } else {
            StringJoiner sj = new StringJoiner("\n", "", "\n");
            sj.add("Found them! Here are the matching tasks in my list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                sj.add(String.format(
                        "%d. %s",
                        i + 1,
                        foundTasks.get(i)));
            }
            return sj.toString();
        }
    }

    /**
     * Prints out reply when the bot receives a illegal command.
     */
    public String showIllegalCommand() {
        return "Sorry I do not understand that command :(";
    }

    /**
     * Prints out a reply when there is a loading error.
     */
    public String showLoadingError() {
        return "An error occurred when loading the data file";
    }

    /**
     * Prints out the error message when an error occurs.
     * @param errorMessage String object that denotes the error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

}

//     ______       ______       ______
//    |   __  \    |   __  \    |   __  \
//    |  |  |  |   |  |  |  |   |  |  |  |
//    |   --  /    |   --  /    |   --  /
//    |   __  \    |   __  \    |   __  \
//    |  |  |  |   |  |  |  |   |  |  |  |
//    |   --  /    |   --  /    |   --  /
//     ------       ------       ------