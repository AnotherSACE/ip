import org.w3c.dom.ls.LSOutput;
import java.util.Scanner;

public class Chrome {

    static String line = "____________________________________________________________";
    static String[] toDoList = new String[100];
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
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                list();
            } else {
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

    public static void add(String task){
        if (currentIndex < 100) {
            toDoList[currentIndex] = task;
            System.out.println(line + "\nadded: " + task + "\n" + line);
            currentIndex++;
        } else {
            System.out.println(line + "\nThis isn't supposed to happen\n" + line);
        }
    }

    public static void list(){
        System.out.println(line);
        for(int i = 0; i < toDoList.length; i++){
            if (toDoList[i] != null) {
                System.out.println((i + 1) + ". " + toDoList[i]);
            }
        }
        System.out.println(line);
    }

}
