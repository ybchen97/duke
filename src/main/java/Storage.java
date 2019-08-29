import java.io.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;

public class Storage {

    /** Path of the file where the task data is stored. */
    private String path;

    /**
     * Constructor for Storage object.
     *
     * @param filePath Path of the file where the task data is stored.
     */
    public Storage(String filePath) {
        this.path = filePath;
    }

    /**
     * Decodes data of file specified by path and returns an ArrayList of Task objects read from the file.
     *
     * @return An ArrayList\<Task\> containing Task objects that are decoded from the file specified by path.
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {

        // taskList to store the tasks loaded
        ArrayList<Task> taskList = new ArrayList<>();

        // read the content from file
        BufferedReader bufferedReader;
        String line;
        try {
            bufferedReader = new BufferedReader(new FileReader(this.path));
            line = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found :(");
        } catch (IOException e) {
            throw new DukeException("An error occurred while trying to read the file");
        }

        while (line != null) {

            switch (line.charAt(0)) {

            case 'T': {
                // Parsing task information
                Matcher matcher = Parser.TODO_TASK_FORMAT.matcher(line);
                if (!matcher.matches()) {
                    // todo duke decoding exception
                    System.out.println("Had trouble decoding Todo task!");
                    break;
                }

                Task t = new Todo(matcher.group("taskDetails"));
                if (matcher.group("isCompleted").equals("1")) {
                    t.complete();
                }

                taskList.add(t);
                break;
            }

            case 'D': {
                // Parsing task information
                Matcher matcher = Parser.DEADLINE_TASK_FORMAT.matcher(line);
                if (!matcher.matches()) {
                    // todo duke decoding exception
                    System.out.println("Had trouble decoding Deadline task!");
                    break;
                }

                // Parsing date string into date object
                String format = "dd-mm-yyyy HH:mm";
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                Date date = dateFormat.parse(matcher.group("dateTime"), new ParsePosition(0));

                Task t = new Deadline(matcher.group("taskDetails"), date);
                if (matcher.group("isCompleted").equals("1")) {
                    t.complete();
                }

                taskList.add(t);
                break;
            }

            case 'E': {
                // Parsing task information
                Matcher matcher = Parser.EVENT_TASK_FORMAT.matcher(line);
                if (!matcher.matches()) {
                    // todo duke decoding exception
                    System.out.println("Had trouble decoding Event task!");
                    break;
                }

                // Parsing date string into date object
                String format = "dd-mm-yyyy HH:mm";
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                Date startDate = dateFormat.parse(matcher.group("startDateTime"), new ParsePosition(0));
                Date endDate = dateFormat.parse(matcher.group("endDateTime"), new ParsePosition(0));

                Task t = new Event(matcher.group("taskDetails"), startDate, endDate);
                if (matcher.group("isCompleted").equals("1")) {
                    t.complete();
                }

                taskList.add(t);
                break;
            }

            default:
                // todo duke does not recognize task exception
                System.out.println("bobo_bot does not recognize this task!");
                break;

            }

            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                throw new DukeException("An error occurred while trying to read the file");
            }

        }

        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while trying to close the reader");
        }

        return taskList;

    }

    /**
     * Saves current list of Task objects in taskList into a file specified by path.
     *
     * @param taskList A TaskList object of Tasks objects
     * @throws IOException
     */
    public void save(TaskList taskList) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.path));
        ArrayList<Task> tasks = taskList.getAllTasks();
        for (Task t : tasks) {
            String encodedTask = t.encode();
            bufferedWriter.write(encodedTask);
        }
        bufferedWriter.close();
    }

}
