import java.util.Scanner;

public class Chrome {

    static String line = "____________________________________________________________";
    static int MAX_TASKS = 100;
    static Task[] toDoList = new Task[MAX_TASKS];
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
                "What can I do for you?\n" + line);
    }

    public static void exit(){
        System.out.println("\nBye. Hope to see you again soon!\n" + line);
    }

    public static void add(String description){
        if (currentIndex < MAX_TASKS) {
            Task task = new Task(description);
            task = task.getTask();
            toDoList[currentIndex] = task;
            System.out.println(line + "\nGot it! I've added this task: "
                    + task + "\n");
            currentIndex++;
            System.out.println("Now you have: " + String.valueOf(currentIndex) +
                    " tasks in the list\n" + line);
        } else {
            System.out.println(line + "\nYou've hit the limit on tasks!\n" + line);
        }
    }

    public static void list(){
        System.out.println(line);
        for(int i = 0; i < toDoList.length; i++){
            if (toDoList[i] != null) {
                String listNumber = String.valueOf(i + 1) + ". ";
                System.out.println(listNumber + toDoList[i].toString());
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
