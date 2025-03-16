package chrome;
import chrome.tasks.*;
import java.util.ArrayList;
import chrome.storage.Storage;
import chrome.parser.Parser;

import chrome.ui.Ui;

public class Chrome {

    public static final String LINE = "____________________________________________________________";
    public static ArrayList<Task> toDoList = new ArrayList<>();
    public static int count = 0;
    public static final String PATH = "./data/chrome.txt";
    public static boolean running = true;


    public static void main(String[] args) {
        Storage.load();
        String logo = """
                 CCCC  H   H  RRRR   OOO   M   M  EEEEE\s
                C      H   H  R   R O   O  MM MM  E    \s
                C      HHHHH  RRRR  O   O  M M M  EEEE \s
                C      H   H  R  R  O   O  M   M  E    \s
                 CCCC  H   H  R   R  OOO   M   M  EEEEE\s
                """;
        System.out.println("Welcome to\n\n" + logo);
        Ui.greet();
        while (running) {
            Parser.run();
        }
    }
}