package chrome.parser;

import chrome.storage.Storage;
import chrome.tasklist.TaskList;
import chrome.ui.Ui;
import java.util.Scanner;


/**
 * The Parser class is responsible for processing user input commands.
 * It interprets a user input and returns the corresponding method
 * in other components of the application such as TaskList, Storage,
 * and Ui classes. Commands include adding, marking, deleting,
 * and listing tasks, saving the tasks to a file, finding tasks
 * and exiting the program
 */
public class Parser {
    private final TaskList taskList;
    private final Storage storage;
    private boolean shouldExit = false;
    private final Ui ui;

    /**
     * Constructor for the Parser class.
     *
     * @param taskList The TaskList object that holds the list of tasks.
     * @param storage The Storage object that handles saving and loading tasks from a file.
     * @param ui The Ui object that handles user interface such as exit messages
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Checks whether the program should exit.
     *
     * @return true if the program should exit, false otherwise.
     */
    public boolean shouldExit() {
        return shouldExit;
    }

    /**
     * Main method for processing user input.
     * It reads the user input and performs actions based on the command.
     * Supported commands include "list", "mark", "unmark", "delete",
     * "save", "find", and "bye".
     */
    public void run(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String command = input.split(" ")[0];

        switch (command) {
            case ("list") -> taskList.list();
            case ("mark") -> taskList.mark(input);
            case ("unmark") -> taskList.unmark(input);
            case ("delete") -> taskList.delete(input);
            case ("save") -> storage.save();
            case ("find") -> taskList.find(input);
            case("bye") -> {
                ui.exit();
                shouldExit = true;
            }
            default -> taskList.add(input);
        }
    }
}
