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
     * @throws DukeNoArgumentsException Thrown if no arguments are inputted with the command.
     * @throws DukeIllegalArgumentException Thrown if an illegal argument is inputted.
     */
    public static Command parse(String fullCommand) throws DukeNoArgumentsException, DukeIllegalArgumentException {
        // Split the string by the first space
        String[] command = fullCommand.split("\\s", 2);
        Command commandToExecute;

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
            String[] taskDetails;

            // Check if arguments are missing
            try {
                taskDetails = command[1].trim().split(" /by ");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeNoArgumentsException("Missing argument for Deadline!");
            }

            // Check date format
            Pattern dateFormat = Pattern.compile("\\d{2}/\\d{2}/\\d{4}\\s\\d{2}\\d{2}");
            Matcher matcher = dateFormat.matcher(taskDetails[1].trim());
            if (!matcher.matches()) {
                throw new DukeIllegalArgumentException("Illegal date format! Follow <dd/mm/yyyy HHmm>!");
            }

            commandToExecute = new AddDeadlineCommand(taskDetails[0].trim(), taskDetails[1].trim());
            break;
        }
        
        case "event": {
            String[] taskDetails;

            // Check if arguments are missing
            try {
                taskDetails = command[1].trim().split(" /at ");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeNoArgumentsException("Missing argument for Event!");
            }

            // Check date format
            Pattern dateFormat = Pattern.compile("(?<date>\\d{2}/\\d{2}/\\d{4})\\s"
                    + "(?<startTime>\\d{2}\\d{2})-(?<endTime>\\d{2}\\d{2})");
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
            String keyword;

            // Checks if argument is missing
            try {
                keyword = command[1];
            } catch (IndexOutOfBoundsException e) {
                throw new DukeNoArgumentsException(
                        "Missing argument for find! Please follow the format: find <keyword>!");
            }

            // Throws exception if more than one keyword is provided
            if (command[1].split(" ").length > 1) {
                throw new DukeIllegalArgumentException(
                        "Please only give a single keyword to find! Follow the format: find <keyword>!");
            }

            commandToExecute = new FindCommand(keyword);
            break;
        }

        case "snooze": {
            String[] arguments;
            try {
                arguments = command[1].split(" ");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeNoArgumentsException("Missing arguments for snooze! Please follow this format: "
                        + "snooze <taskNum> <length> <unit>");
            }
            if (arguments.length != 3) {
                // todo refactor to wrong number of arguments duke exception
                throw new DukeIllegalArgumentException("Wrong number of arguments! Please follow this format: "
                        + "snooze <taskNum> <length> <unit>");
            }

            int taskNum = Integer.parseInt(arguments[0]) - 1;
            int length = Integer.parseInt(arguments[1]);
            String unit = arguments[2];

            commandToExecute = new SnoozeCommand(taskNum, length, unit);
            break;
        }
        
        default:
            commandToExecute = new IllegalCommand();
            break;
        }
        return commandToExecute;
    }

}
