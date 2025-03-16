package chrome.parser;

import chrome.storage.Storage;
import chrome.tasklist.TaskList;
import chrome.ui.Ui;
import java.util.Scanner;

public class Parser {
    private final TaskList taskList;
    private final Storage storage;
    private boolean shouldExit = false;
    private final Ui ui;

    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

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
            case("bye") -> {
                ui.exit();
                shouldExit = true;
            }
            default -> taskList.add(input);
        }
    }

    public boolean shouldExit() {
        return shouldExit;
    }
}
