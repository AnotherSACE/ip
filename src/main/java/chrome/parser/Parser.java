package chrome.parser;

import chrome.storage.Storage;
import chrome.tasklist.TaskList;
import chrome.ui.Ui;
import static chrome.Chrome.running;

import java.util.Scanner;

public class Parser {

    public static void run(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String command = input.split(" ")[0];
        switch (command) {
            case ("list"):
                TaskList.list();
                break;
            case ("mark"):
                TaskList.mark(input);
                break;
            case ("unmark"):
                TaskList.unmark(input);
                break;
            case ("delete"):
                TaskList.delete(input);
                break;
            case ("save"):
                Storage.save();
                break;
            case("bye"):
                Ui.exit();
                running = false;
                break;
            default:
                TaskList.add(input);
                break;
        }
    }
}
