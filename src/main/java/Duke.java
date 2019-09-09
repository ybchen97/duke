import java.util.Scanner;

public class Duke {

    /**
     * Storage object that loads and saves tasks from and to the local file system.
     */
    private Storage storage;

    /**
     * TaskList object that stores the list of tasks.
     */
    private TaskList tasks;

    /**
     * Ui object that handles the interactions with the user.
     */
    private Ui ui;

    /**
     * Constructor for Duke object.
     * When Duke object is created, Storage, Ui and TaskList objects are also created and attached as a field member of
     * the Duke object. A file containing the an encoded list of tasks is also loaded into the TaskList object of Duke.
     *
     * @param filePath Path of the file that contains the tasks to be loaded into Duke.
     */
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

    /**
     * Run the main logic of the Duke program.
     */
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

    public String getResponse(String input) {
        String response;
        // todo refactor isExit to quit the GUI when isExit is true
        boolean isExit;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }
        return response;
    }

    public String showWelcome() {
        return ui.showWelcome();
    }

    /**
     * Main method.
     * @param args String arguments to main method.
     */
    public static void main(String[] args) {

        // Initializing Duke and loading data from hard drive
        String dataPath = "/data/dukeData.txt";
        Duke duke = new Duke(dataPath);

        duke.run();

    }
}
