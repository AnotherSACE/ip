package chrome;
import chrome.tasklist.TaskList;
import chrome.tasks.*;
import chrome.storage.Storage;
import chrome.parser.Parser;
import chrome.ui.Ui;
import java.util.ArrayList;

/**
 * The Chrome class serves as the main controller for the application.
 * It initializes the program components such as Storage, Parser, and Ui,
 * and implements the main functions of the program such as loading tasks,
 * greeting the user, and running the command loop until the user exits.
 */
public class Chrome {
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructor for the Chrome class.
     * Initializes the Storage, Ui, and Parser components of the program.
     */
    public Chrome() {
        this.storage = new Storage("./data/chrome.txt");
        ArrayList<Task> toDoList = storage.getTasks();
        this.ui = new Ui();
        this.parser = new Parser(new TaskList(toDoList), storage, ui);
    }

    /**
     * Starts the Chrome program and begins running the command loop.
     * Loads tasks from the storage and displays a welcome message.
     * Continues running until the user decides to exit.
     */
    public void run() {
        storage.load();
        String logo = """
                 CCCC  H   H  RRRR   OOO   M   M  EEEEE\s
                C      H   H  R   R O   O  MM MM  E    \s
                C      HHHHH  RRRR  O   O  M M M  EEEE \s
                C      H   H  R  R  O   O  M   M  E    \s
                 CCCC  H   H  R   R  OOO   M   M  EEEEE\s
                """;
        System.out.println("Welcome to\n\n" + logo);
        ui.greet();
        while (!parser.shouldExit()) {
            parser.run();
        }
    }

    /**
     * Main method to start the Chrome program.
     * Creates an instance of Chrome and starts the run method.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Chrome chrome = new Chrome();
        chrome.run();
    }
}