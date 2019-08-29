import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private String hLine = "    ____________________________________________________________";
    private ArrayList<Task> taskList;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {

        // run the main program
        ui.showWelcome();

        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = sc.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

        sc.close();

    }


    public static void main(String[] args) {

        // Initializing Duke and loading data from hard drive
        String dataPath = "../../../data/dukeData.txt";
        Duke duke = new Duke(dataPath);

        duke.run();

    }
}
