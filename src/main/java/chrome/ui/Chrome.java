package chrome.ui;
import chrome.exceptions.DoneException;
import chrome.exceptions.InvalidInputException;
import chrome.exceptions.InvalidNumberException;
import chrome.tasks.Task;
import java.util.ArrayList;

import java.util.Scanner;

public class Chrome {

    static final String LINE = "____________________________________________________________";
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
                case ("delete"):
                    delete(input);
                    break;
                default:
                    add(input);
                    break;
            }
        }
    }

    public static void greet(){
        System.out.println(LINE + "\nHello! I'm Chrome \n" +
                "How can I help you today?\n" + LINE);
    }

    public static void exit(){
        System.out.println("\nHave a nice day!\n" + LINE);
    }

    public static void add(String description){
            Task task = new Task(description);
            try {
                task = task.getTask();
            } catch (InvalidInputException e) {
                System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
                return;
            }
            toDoList.add(task);
            System.out.println(LINE + "\nGot it! I've added this task: "
                    + task + "\n");
            String plural;
            if (count == 0) {
                plural = "";
            } else {
                plural = "s";
            }
            count++;
            System.out.println("Now you have: " + String.valueOf(count) +
                    " task" + plural +" in the list\n" + LINE);
        }

    public static void list(){
        System.out.println(LINE);
        int index = 1;
        for(Task task : toDoList){
            if (task != null) {
                System.out.println(index + ". " + task.toString());
                index++;
            }
        }
        System.out.println(LINE);
    }

    public static void mark(String input){
        String[] parts = input.split(" ");
        int index = 0;
        try {
            index = Integer.parseInt(parts[1]) - 1;
            if (index < 0) {
                throw new InvalidNumberException("Task number must start from 1");
            }
        } catch (NumberFormatException e) {
                System.out.println(LINE + "\nInvalid number!\n" + LINE);
                return;
        } catch (InvalidNumberException e) {
            System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
            return;
        }
        try {
            toDoList.get(index).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE + "\nTask doesn't exist!\n" + LINE);
            return;
        } catch (DoneException e) {
            System.out.println(LINE + "\nTask already marked as done\n" + LINE);
            return;
        }
        System.out.println(LINE + "\nNice! I've marked this task as done:\n"
        + toDoList.get(index).toString() + "\n" + LINE);
    }

    public static void unmark(String input){
        String[] parts = input.split(" ");
        int index = 0;
        try {
            index = Integer.parseInt(parts[1]) - 1;
            if (index < 0) {
                throw new InvalidNumberException("Task number must start from 1");
            }
        } catch (NumberFormatException e) {
            System.out.println(LINE + "\nInvalid number!\n" + LINE);
            return;
        } catch (InvalidNumberException e) {
            System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
            return;
        }
        try {
            toDoList.get(index).setDone(false);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE + "\nTask doesn't exist!\n" + LINE);
            return;
        } catch (DoneException e) {
            System.out.println(LINE + "\nTask already marked as undone\n" + LINE);
            return;
        }
        System.out.println(LINE + "\nOK, I've marked this task as not done yet:\n"
        + toDoList.get(index).toString() + "\n" + LINE);
    }

    public static void delete(String input){
        String[] parts = input.split(" ");
        int index = 0;
        Task task = null;
        try {
            index = Integer.parseInt(parts[1]) - 1;
            if (index < 0) {
            throw new InvalidNumberException("Task number must start from 1");
            }
        } catch (NumberFormatException e) {
            System.out.println(LINE + "\nInvalid number!\n" + LINE);
            return;
        } catch (InvalidNumberException e) {
            System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
            return;
        }
        try {
            task = toDoList.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE + "\nTask doesn't exist!\n" + LINE);
            return;
        }
        count = count - 1;
        toDoList.remove(index);
        String plural;
        if (count == 0) {
            plural = "";
        } else {
            plural = "s";
        }
        System.out.println(LINE + "\nOk . I've removed this task:\n" + task.toString() +
                "\nYou now have " + String.valueOf(count) + " task" + plural + " in the list!\n" + LINE);
    }

}
