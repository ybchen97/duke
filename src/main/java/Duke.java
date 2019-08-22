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

    public void answer(Supplier<Consumer<Task>> supplier) {
        System.out.println(this.hLine);
        supplier.get();
        System.out.println(this.hLine);
    }

    public void greeting() {
        this.answer("Hello! I'm Duke\n     What can I do for you?");
    }

    public void farewell() {
        this.answer("Bye. Hope to see you again soon!");
    }

    private void numberOfTasks() {
        int size = this.taskList.size();
        if (size == 1) {
            System.out.println("     Now you have 1 task in the list");
        } else {
            System.out.println("     Now you have " + this.taskList.size() + " tasks in the list");
        }
    }

    public void addTodo(String input) throws DukeNoArgumentsException {

        if (input.trim().isEmpty()) {
            throw new DukeNoArgumentsException("No arguments for todo provided!");
        }
        Task task = new Todo(input.trim());
        this.taskList.add(task);

        // Printing out messages
        System.out.println(this.hLine);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        this.numberOfTasks();
        System.out.println(this.hLine);

    }

    public void addDeadline(String input) throws DukeNoArgumentsException {

        if (input.isEmpty()) {
            throw new DukeNoArgumentsException("No arguments for deadline provided!");
        }
        
        String[] details = input.split("/by");
        Task task = new Deadline(details[0].trim(), details[1].trim());
        this.taskList.add(task);

        // Printing out messages
        System.out.println(this.hLine);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        this.numberOfTasks();
        System.out.println(this.hLine);

    }

    public void addEvent(String input) throws DukeNoArgumentsException {
        
        if (input.isEmpty()) {
            throw new DukeNoArgumentsException("No arguments for event provided!");
        }
        
        String[] details = input.split("/at");
        Task task = new Event(details[0].trim(), details[1].trim());

        this.taskList.add(task);

        // Printing out messages
        System.out.println(this.hLine);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        this.numberOfTasks();
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

        Task task = this.taskList.get(num - 1);
        task.complete();
        this.answer("Nice! I've marked this task as done: \n       " + task.toString());

    }

    public void delete(int num) throws IndexOutOfBoundsException {

        Task task = this.taskList.remove(num - 1);

        System.out.println(this.hLine);
        System.out.println("     Noted. I've removed this task:\n       " + task);
        this.numberOfTasks();
        System.out.println(this.hLine);
        
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

        // Input handler
        boolean isRunning = true;
        while (isRunning) {

            String command = sc.next();

            switch (command) {

                case "bye":
                    duke.farewell();
                    isRunning = false;
                    break;
                
                case "list":
                    duke.list();
                    break;
                
                case "done": {
                    String unsanitzedTaskNum = sc.nextLine();
                    int taskNum;
                    try {
                        taskNum = Integer.parseInt(unsanitzedTaskNum.trim());
                        duke.complete(taskNum);
                    } catch (NumberFormatException e) {
                        duke.answer("Please type \"done <number>\" where <number> is an integer");
                    } catch (IndexOutOfBoundsException e) {
                        duke.answer("Task does not exist!");
                    } 
                    break;
                }

                case "delete": {
                    String unsanitzedTaskNum = sc.nextLine();
                    int taskNum;
                    try {
                        taskNum = Integer.parseInt(unsanitzedTaskNum.trim());
                        duke.delete(taskNum);
                    } catch (NumberFormatException e) {
                        duke.answer("Please type \"delete <number>\" where <number> is an integer");
                    } catch (IndexOutOfBoundsException e) {
                        duke.answer("Task does not exist!");
                    } 
                    break;
                }

                case "todo": {
                    String todoArgs = sc.nextLine();
                    try {
                        duke.addTodo(todoArgs);
                    } catch (DukeNoArgumentsException e) {
                        duke.answer("\u2639 OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                }

                case "deadline": {
                    String todoArgs = sc.nextLine();
                    try {
                        duke.addDeadline(todoArgs);
                    } catch (DukeNoArgumentsException e) {
                        duke.answer("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                    }
                    break;
                }

                case "event": {
                    String todoArgs = sc.nextLine();
                    try {
                        duke.addEvent(todoArgs);
                    } catch (DukeNoArgumentsException e) {
                        duke.answer("\u2639 OOPS!!! The description of a event cannot be empty.");
                    }
                    break;
                }

                default:
                    duke.answer("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-("); 
                    sc.nextLine();

            }
        }

        sc.close();

    }
}
