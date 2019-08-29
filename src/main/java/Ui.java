import java.util.ArrayList;

public class Ui {

    private String line = "    ____________________________________________________________";

    private void answer(String input) {
        System.out.println(this.line);
        System.out.println("     " + input);
        System.out.println(this.line);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.answer("Hello! I'm Bobo_bot\n     What can I do for you?");
    }

    public void showFarewell() {
        this.answer("Bye. Hope to see you again soon!");
    }

    public void showAddTask(Task t) {
        // Printing out messages
        System.out.println(this.line);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + t);
        System.out.println(this.line);
    }

    public void showDone(Task t) {
        this.answer("Nice! I've marked this task as done: \n       " + t.toString());
    }

    public void showDelete(Task t) {
        System.out.println(this.line);
        System.out.println("     Noted. I've removed this task:\n       " + t);
        System.out.println(this.line);
    }

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

    public void showIllegalCommand() {
        this.answer("Sorry I do not understand that command :(");
    }

    public void showLoadingError() {
        this.answer("An error occurred when loading the data file");
    }

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