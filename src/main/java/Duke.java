import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

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
        this.answer("Hello! I'm Bobo_bot\n     What can I do for you?");
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

    public void loadData(String path) throws IOException {

        // read the content from file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = bufferedReader.readLine();

        while (line != null) {

            String[] task = line.split(" [|] ");
            switch (task[0]) {
            
            case "T": {
                Task t = new Todo(task[2]);
                if (task[1].equals("1")) {
                    t.complete();
                }
                this.taskList.add(t);
                break;
            }

            case "D": {
                Task t = new Deadline(task[2], task[3]);
                if (task[1].equals("1")) {
                    t.complete();
                }
                this.taskList.add(t);
                break;
            }

            case "E": {
                Task t = new Event(task[2], task[3]);
                if (task[1].equals("1")) {
                    t.complete();
                }
                this.taskList.add(t);
                break;
            }

            default:
                System.out.println("bobo_bot does not recognize this task!");
                break;
            }
            
            line = bufferedReader.readLine();

        }

        bufferedReader.close();

    }

    // public void saveData(String path) throws IOException {

    //     // write the content in file 
    //     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

    //     String fileContent = "This is a sample text.";
    //     bufferedWriter.write(fileContent);

    //     bufferedWriter.close();

    // }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Initializing Duke and loading data from hard drive
        Duke duke = new Duke();
        String dataPath = "../../../data/dukeData.txt";
        try {
            duke.loadData(dataPath);
        } catch (FileNotFoundException e) {
            System.out.println("bobo_bot cannot find the data file to load! Please re-specify your file path!");
        } catch (IOException e) {
            System.out.println("An error occurred when reading the file! :(");
        }

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
                String unsanitizedTaskNum = sc.nextLine();
                int taskNum;
                try {
                    taskNum = Integer.parseInt(unsanitizedTaskNum.trim());
                    duke.complete(taskNum);
                } catch (NumberFormatException e) {
                    duke.answer("Please type \"done <number>\" where <number> is an integer");
                } catch (IndexOutOfBoundsException e) {
                    duke.answer("Task does not exist!");
                } 
                break;
            }

            case "delete": {
                String unsanitizedTaskNum = sc.nextLine();
                int taskNum;
                try {
                    taskNum = Integer.parseInt(unsanitizedTaskNum.trim());
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
                break;
            }
        }

        sc.close();

    }
}
