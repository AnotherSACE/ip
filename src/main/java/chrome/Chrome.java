package chrome;
import chrome.tasklist.TaskList;
import chrome.tasks.*;
import chrome.storage.Storage;
import chrome.parser.Parser;
import chrome.ui.Ui;
import java.util.ArrayList;

public class Chrome {
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    public Chrome() {
        this.storage = new Storage("./data/chrome.txt");
        ArrayList<Task> toDoList = storage.getTasks();
        this.ui = new Ui();
        this.parser = new Parser(new TaskList(toDoList), storage, ui);
    }

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

    public static void main(String[] args) {
        Chrome chrome = new Chrome();
        chrome.run();
    }
}