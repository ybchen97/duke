import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private String hLine = "    ____________________________________________________________";
    private ArrayList<Task> store = new ArrayList<>();

    public void answer(ArrayList<Task> input) {
        System.out.println(this.hLine);
        int size = input.size();
        for (int i = 0; i < size; i++) {
            System.out.println(String.format(
                "     %d. %s", 
                i + 1, 
                input.get(i)));
        }
        System.out.println(this.hLine);
    }

    public void answer(String input) {
        System.out.println(this.hLine);
        System.out.println("     " + input);
        System.out.println(this.hLine);
    }

    public void greeting() {
        this.answer("Hello! I'm Duke\n     What can I do for you?");
    }

    public void farewell() {
        this.answer("Bye. Hope to see you again soon!");
    }

    public void echo(String input) {
        this.answer(input);
    }

    public void add(String input) {
        this.store.add(new Task(input));
        this.answer("added: " + input);
    }

    public void list() {
        this.answer(this.store);
    }

    public void complete(int num) throws IndexOutOfBoundsException {
        Task task = this.store.get(num -1);
        task.complete();
        this.answer("Nice! I've marked this task as done: \n      " + task.toString());
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();

        // Greeting from Duke
        duke.greeting();

        // Taking in user input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Input handler
        boolean isRunning = true;
        while (isRunning) {
            if (input.equals("bye")) {
                duke.farewell();
                isRunning = false;
                break;
            } else if (input.equals("list")) {
                duke.list();
            } else if (input.contains("done")) {
                String unsanitzedTaskNum = input.substring(5);
                int taskNum;
                try {
                    taskNum = Integer.parseInt(unsanitzedTaskNum);
                    duke.complete(taskNum);
                } catch (NumberFormatException e) {
                    duke.answer("Please type \"done <number>\" where <number> is an integer");
                } catch (IndexOutOfBoundsException e) {
                    duke.answer("Task does not exist!");
                } 
            } else {
                duke.add(input);
            }
            input = sc.nextLine();
        }
        sc.close();
    }
}
