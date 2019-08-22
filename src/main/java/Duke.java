import java.util.Scanner;
import java.util.ArrayList;
import java.util.function.*;

public class Duke {

    private String hLine = "    ____________________________________________________________";
    private ArrayList<Task> taskList;

    public Duke() {
        this.taskList = new ArrayList<Task>();
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

    public void add(String input) {
        Task task;
        if (input.contains("todo")) {

            String description = input.substring(5);
            task = new Todo(description);

        } else if (input.contains("deadline")) {
            
            String description = input.substring(9);
            String[] details = description.split("/by");
            System.out.println(details.toString());
            task = new Deadline(details[0].trim(), details[1].trim());

        } else {

            String description = input.substring(6);
            String[] details = description.split("/at");
            task = new Event(details[0].trim(), details[1].trim());

        }

        this.taskList.add(task);

        // Printing out messages
        System.out.println(this.hLine);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        int size = this.taskList.size();
        if (size == 1) {
            System.out.println("     Now you have 1 task in the list");
        } else {
            System.out.println("     Now you have " + this.taskList.size() + " tasks in the list");
        }
        System.out.println(this.hLine);
    }

    public void list() {
        System.out.println(this.hLine);
        System.out.println("     Here are your tasks in your list:");

        int size = this.taskList.size();
        for (int i = 0; i < size; i++) {
            System.out.println(String.format(
                "     %d. %s", 
                i + 1, 
                this.taskList.get(i)));
        }

        System.out.println(this.hLine);
    }

    public void complete(int num) throws IndexOutOfBoundsException {
        Task task = this.taskList.get(num -1);
        task.complete();
        this.answer("Nice! I've marked this task as done: \n       " + task.toString());
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
            }
            
            else if (input.equals("list")) {
                duke.list();
            }
            
            else if (input.contains("done")) {
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
            }

            // else if (input.contains("todo")) {
            //     duke.addTodo(input);
            // }

            // else if (input.contains("deadline")) {
            //     duke.addDeadline(input);
            // }

            // else if (input.contains("event")) {
            //     duke.addEvent(input);
            // }
            
            else {
                duke.add(input);
            }

            input = sc.nextLine();

        }

        sc.close();

    }
}
