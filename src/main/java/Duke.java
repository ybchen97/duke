import java.util.Scanner;

public class Duke {

    private String hLine = "    ____________________________________________________________";

    public void answer(String input) {
        System.out.println(this.hLine);
        System.out.println("    " + input);
        System.out.println(this.hLine);
    }

    public void greeting() {
        this.answer("Hello! I'm Duke\n    What can I do for you?");
    }

    public void farewell() {
        this.answer("Bye. Hope to see you again soon!");
    }

    public void echo(String input) {
        this.answer(input);
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

        // Echo
        while (!input.equals("bye")) {
            duke.echo(input);
            input = sc.nextLine();
        }

        duke.farewell();
        sc.close();

    }
}
