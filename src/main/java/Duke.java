import java.util.Scanner;
import java.awt.image.IndexColorModel;
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

    public void addTodo(String input) throws IndexOutOfBoundsException {

        Task task;
        
        String description = input.substring(5);
        task = new Todo(description);

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

    public void addDeadline(String input) {

        Task task;
        
        String description = input.substring(9);
        String[] details = description.split("/by");
        System.out.println(details.toString());
        task = new Deadline(details[0].trim(), details[1].trim());

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

    public void addEvent(String input) {

        Task task;
        
        String description = input.substring(6);
        String[] details = description.split("/at");
        task = new Event(details[0].trim(), details[1].trim());

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

        int size = this.taskList.size();
        if (size == 0) {
            System.out.println("     You have no tasks currently.");
        } else {
            System.out.println("     Here are your tasks in your list:");
            for (int i = 0; i < size; i++) {
                System.out.println(String.format(
                    "     %d. %s", 
                    i + 1, 
                    this.taskList.get(i)));
            }
        }

        System.out.println(this.hLine);
    }

    public void complete(int num) throws IndexOutOfBoundsException {
        Task task = this.taskList.get(num -1);
        task.complete();
        this.answer("Nice! I've marked this task as done: \n       " + task.toString());
    }

    public void delete(int num) throws IndexOutOfBoundsException {
        Task task = this.taskList.remove(num-1);
        this.answer("Noted. I've removed this task:\n       " + task + "\n     Now you have " + this.taskList.size() + " tasks in the list");
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

            else if (input.contains("delete")) {
                String unsanitzedTaskNum = input.substring(7);
                int taskNum;
                try {
                    taskNum = Integer.parseInt(unsanitzedTaskNum);
                    duke.delete(taskNum);
                } catch (NumberFormatException e) {
                    duke.answer("Please type \"delete <number>\" where <number> is an integer");
                } catch (IndexOutOfBoundsException e) {
                    duke.answer("Task does not exist!");
                } 
            }

            else if (input.contains("todo")) {
                try {
                    duke.addTodo(input);
                } catch (IndexOutOfBoundsException e) {
                    duke.answer("\u2639 OOPS!!! The description of a todo cannot be empty.");
                }
            }

            else if (input.contains("deadline")) {
                try {
                    duke.addDeadline(input);
                } catch (IndexOutOfBoundsException e) {
                    duke.answer("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                }
            }

            else if (input.contains("event")) {
                try {
                    duke.addEvent(input);
                } catch (IndexOutOfBoundsException e) {
                    duke.answer("\u2639 OOPS!!! The description of a event cannot be empty.");
                }
            }

            else {
                duke.answer("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-("); 
            }

            input = sc.nextLine();

        }

        sc.close();

    }
}
