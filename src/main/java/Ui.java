import java.util.ArrayList;

public class Ui {

    /** Horizontal line used to separate replies from user input. */
    private String line = "    ____________________________________________________________";

    /**
     * Prints bot replies in a format preceded by spaces and enclosed by horizontal lines.
     * @param input A String object to be enclosed within the output format.
     */
    private void answer(String input) {
        System.out.println(this.line);
        System.out.println("     " + input);
        System.out.println(this.line);
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.answer("Hello! I'm Bobo_bot\n     What can I do for you?");
    }

    /**
     * Prints the farewell message.
     */
    public void showFarewell() {
        this.answer("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the task that has been just added by the user.
     * @param t Task object that has been added by the user.
     */
    public void showAddTask(Task t) {
        // Printing out messages
        System.out.println(this.line);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + t);
        System.out.println(this.line);
    }

    /**
     * Prints out the task that has been completed by the user.
     * @param t Task object that has been completed by the user.
     */
    public void showDone(Task t) {
        this.answer("Nice! I've marked this task as done: \n       " + t.toString());
    }

    /**
     * Prints out the task that has been deleted by the user.
     * @param t Task object that has been deleted by the user.
     */
    public void showDelete(Task t) {
        System.out.println(this.line);
        System.out.println("     Noted. I've removed this task:\n       " + t);
        System.out.println(this.line);
    }

    /**
     * Prints out the current list of tasks that the bot contains.
     * @param tasks An ArrayList of Task objects. Denotes the current list of tasks that the bot contains.
     */
    public void showList(ArrayList<Task> tasks) {
        System.out.println(this.line);

        int size = tasks.size();
        if (size == 0) {
            System.out.println("     You have no tasks currently.");
        } else {
            System.out.println("     Here are your tasks in your list:");
            for (int i = 0; i < size; i++) {
                System.out.println(String.format(
                        "     %d. %s",
                        i + 1,
                        tasks.get(i)));
            }
        }

        System.out.println(this.line);
    }

    /**
     * Prints out the tasks in the list of tasks that match the keyword given by the user.
     * @param foundTasks ArrayList of Task objects that match the keyword given by the user.
     */
    public void showFindTask(ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            this.answer("Sorry I can't seem to find this task in my list :(");
        } else {
            System.out.println(this.line);
            System.out.println("     Found them! Here are the matching tasks in my list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println(String.format(
                        "     %d. %s",
                        i + 1,
                        foundTasks.get(i)));
            }
            System.out.println(this.line);
        }
    }

    /**
     * Prints out reply when the bot receives a illegal command.
     */
    public void showIllegalCommand() {
        this.answer("Sorry I do not understand that command :(");
    }

    /**
     * Prints out a reply when there is a loading error.
     */
    public void showLoadingError() {
        this.answer("An error occurred when loading the data file");
    }

    /**
     * Prints out the error message when an error occurs.
     * @param errorMessage String object that denotes the error message.
     */
    public void showError(String errorMessage) {
        this.answer(errorMessage);
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