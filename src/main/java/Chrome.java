import org.w3c.dom.ls.LSOutput;
import java.util.Scanner;

public class Chrome {

    static String line = "____________________________________________________________";
    static Task[] toDoList = new Task[100];
    static int currentIndex = 0;

    public static void main(String[] args) {
        String logo = " CCCC  H   H  RRRR   OOO   M   M  EEEEE \n"
                + "C      H   H  R   R O   O  MM MM  E     \n"
                + "C      HHHHH  RRRR  O   O  M M M  EEEE  \n"
                + "C      H   H  R  R  O   O  M   M  E     \n"
                + " CCCC  H   H  R   R  OOO   M   M  EEEEE \n";
        System.out.println("Hello from\n\n" + logo);
        greet();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String command = input.split(" ")[0];
            if (input.equals("bye")){
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
            }
        }
    }

    public static void greet(){
        System.out.println(line + "\nHello! I'm Chrome \n" +
                "What can I do for you?\n" + line);
    }

    public static void exit(){
        System.out.println("\nBye. Hope to see you again soon!\n" + line);
    }

    public static void echo(String sentence){
        if (!sentence.equals("bye")) {
            System.out.println(line + "\n" + sentence + "\n" + line);
        }
    }

    public static void add(String description){
        if (currentIndex < 100) {
            Task task = new Task(description);
            toDoList[currentIndex] = task;
            System.out.println(line + "\nadded: " + task.getName() + "\n" + line);
            currentIndex++;
        } else {
            System.out.println(line + "\nThis isn't supposed to happen\n" + line);
        }
    }

    public static void list(){
        System.out.println(line);
        for(int i = 0; i < toDoList.length; i++){
            if (toDoList[i] != null) {
                    System.out.println((i + 1) + "." + toDoList[i].toString());
            }
        }
        System.out.println(line);
    }

    public static void mark(String input){
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        toDoList[index].setDone(true);
        System.out.println(line + "\nNice! I've marked this task as done:\n"
        + toDoList[index].toString() + "\n" + line);
    }

    public static void unmark(String input){
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        toDoList[index].setDone(false);
        System.out.println(line + "\nOK, I've marked this task as not done yet:\n"
        + toDoList[index].toString() + "\n" + line);
    }

}
