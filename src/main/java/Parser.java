import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Parser format used to decode Todo tasks.
     */
    public static final Pattern TODO_TASK_FORMAT = Pattern.compile("^T[|]"
            + "(?<isCompleted>\\d)" + "[|]"
            + "(?<taskDetails>.+)");

    /**
     * Parser format used to decode Deadline tasks.
     */
    public static final Pattern DEADLINE_TASK_FORMAT = Pattern.compile("^D[|]"
            + "(?<isCompleted>\\d)" + "[|]"
            + "(?<taskDetails>.+)" + "[|]"
            + "(?<dateTime>\\d{2}-\\d{2}-\\d{4}\\s\\d{2}:\\d{2})");

    /**
     * Parser format used to decode Event tasks.
     */
    public static final Pattern EVENT_TASK_FORMAT = Pattern.compile("^E[|]"
            + "(?<isCompleted>\\d)" + "[|]"
            + "(?<taskDetails>.+)" + "[|]"
            + "(?<startDateTime>\\d{2}-\\d{2}-\\d{4}\\s\\d{2}:\\d{2})" + "[|]"
            + "(?<endDateTime>\\d{2}-\\d{2}-\\d{4}\\s\\d{2}:\\d{2})");

    /**
     * Parses the user input and returns a Command object which corresponds to the command the user gave in the input.
     *
     * @param fullCommand A string provided from the user input.
     * @return A Command object that corresponds to which command the user gives in the input.
     * @throws DukeNoArgumentsException
     * @throws DukeIllegalArgumentException
     */
    public static Command parse(String fullCommand) throws DukeNoArgumentsException, DukeIllegalArgumentException {
        String[] command = fullCommand.split("\\s", 2);
        // todo refactor away this null assignment in parse()
        Command commandToExecute = null;

        switch (command[0]) {

        case "bye" :
            commandToExecute = new ByeCommand();
            break;

        case "list" :
            commandToExecute = new ListCommand();
            break;

        case "done" :
            // todo error handling message for parseInt
            try {
                int taskNum = Integer.parseInt(command[1].trim());
                commandToExecute = new DoneCommand(taskNum);
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException("Please type \"done <number>\" where <number> is an integer");
            }
            break;

        case "delete" :
            // todo error handling message for parseInt
            try {
                int taskNum = Integer.parseInt(command[1].trim());
                commandToExecute = new DeleteCommand(taskNum);
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException("Please type \"done <number>\" where <number> is an integer");
            }
            break;

        case "todo" :
            // todo error handling message for no arguments exception
            try {
                commandToExecute = new AddTodoCommand(command[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeNoArgumentsException("Missing argument for Todo!");
            }
            break;
            
        case "deadline" : {
            // todo error handling message for illegal date argument
            String[] taskDetails = command[1].trim().split(" /by ");
            Pattern dateFormat = Pattern.compile("\\d{2}/\\d{2}/\\d{4}\\s\\d{2}\\d{2}");
            Matcher matcher = dateFormat.matcher(taskDetails[1].trim());
            if (!matcher.matches()) {
                throw new DukeIllegalArgumentException("Illegal date format! Follow <dd/mm/yyyy HHmm>!");
            }

            commandToExecute = new AddDeadlineCommand(taskDetails[0].trim(), taskDetails[1].trim());
            break;
        }
        
        case "event": {
            String[] taskDetails = command[1].trim().split(" /at ");
            Pattern dateFormat = Pattern.compile("(?<date>\\d{2}/\\d{2}/\\d{4})\\s(?<startTime>\\d{2}\\d{2})-(?<endTime>\\d{2}\\d{2})");
            Matcher matcher = dateFormat.matcher(taskDetails[1].trim());
            if (!matcher.matches()) {
                throw new DukeIllegalArgumentException("Illegal date format! Follow <dd/mm/yyyy HHmm-HHmm>!");
            }

            String startDateTime = matcher.group("date") + " " + matcher.group("startTime");
            String endDateTime = matcher.group("date") + " " + matcher.group("endTime");
            commandToExecute = new AddEventCommand(taskDetails[0].trim(), startDateTime, endDateTime);
            break;
        }

        case "find": {
            // todo error handling when user provides more than one word as argument
            String keyword = command[1];
            commandToExecute = new FindCommand(keyword);
            break;
        }
        
        default:
            commandToExecute = new IllegalCommand();
            break;
        }
        return commandToExecute;
    }

}
