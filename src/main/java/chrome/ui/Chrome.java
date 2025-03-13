package chrome.ui;
import chrome.exceptions.DoneException;
import chrome.exceptions.InvalidInputException;
import chrome.tasks.Task;
import java.util.ArrayList;

import java.util.Scanner;

public class Chrome {

    static String line = "____________________________________________________________";
    static ArrayList<Task> toDoList = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) {
        String logo = " CCCC  H   H  RRRR   OOO   M   M  EEEEE \n"
                + "C      H   H  R   R O   O  MM MM  E     \n"
                + "C      HHHHH  RRRR  O   O  M M M  EEEE  \n"
                + "C      H   H  R  R  O   O  M   M  E     \n"
                + " CCCC  H   H  R   R  OOO   M   M  EEEEE \n";
        System.out.println("Welcome to\n\n" + logo);
        greet();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String command = input.split(" ")[0];
            if (input.equals("bye")){
                exit();
                break;
            }
            switch (command) {
                case ("list"):
                    list();
                    break;
                case ("mark"):
                    mark(input);
                    break;
                case ("unmark"):
                    unmark(input);
                    break;
                default:
                    add(input);
                    break;
            }
        }
    }

    public static void greet(){
        System.out.println(line + "\nHello! I'm Chrome \n" +
                "How can I help you today?\n" + line);
    }

    public static void exit(){
        System.out.println("\nHave a nice day!\n" + line);
    }

    public static void add(String description){
            Task task = new Task(description);
            try {
                task = task.getTask();
            } catch (InvalidInputException e) {
                System.out.println(line + "\n" + e.getMessage() + "\n" + line);
                return;
            }
            toDoList.add(task);
            System.out.println(line + "\nGot it! I've added this task: "
                    + task + "\n");
            String plural = "s";
            if (count == 0) {
                plural = "";
            }
            count++;
            System.out.println("Now you have: " + String.valueOf(count) +
                    " task" + plural +" in the list\n" + line);
        }

    public static void list(){
        System.out.println(line);
        int index = 1;
        for(Task task : toDoList){
            if (task != null) {
                String listNumber = ". ";
                System.out.println(index + ". " + task.toString());
                index++;
            }
        }
        System.out.println(line);
    }

    public static void mark(String input){
        String[] parts = input.split(" ");
        int index;
        try {
        index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println(line + "\nInvalid number!\n" + line);
            return;
        }
        try {
            toDoList.get(index).setDone(true);
        } catch (NullPointerException e) {
            System.out.println(line + "\nTask doesn't exist!\n" + line);
            return;
        } catch (DoneException e) {
            System.out.println(line + "\nTask already marked as done\n" + line);
            return;
        }
        System.out.println(line + "\nNice! I've marked this task as done:\n"
        + toDoList.get(index).toString() + "\n" + line);
    }

    public static void unmark(String input){
        String[] parts = input.split(" ");
        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println(line + "\nInvalid number!\n" + line);
            return;
        }
        try {
            toDoList.get(index).setDone(false);
        } catch (NullPointerException e) {
            System.out.println(line + "\nTask doesn't exist!\n" + line);
            return;
        } catch (DoneException e) {
            System.out.println(line + "\nTask already marked as undone\n" + line);
            return;
        }
        System.out.println(line + "\nOK, I've marked this task as not done yet:\n"
        + toDoList.get(index).toString() + "\n" + line);
    }

}
