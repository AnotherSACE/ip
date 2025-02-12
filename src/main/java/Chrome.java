import org.w3c.dom.ls.LSOutput;
import java.util.Scanner;

public class Chrome {

    static String line = "____________________________________________________________";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(greet());
        echo();
        System.out.println(exit());

    }

    public static String greet(){
        return line + "\nHello! I'm Chrome \n" +
                "What can I do for you?\n" + line;
    }

    public static String exit(){
        return line + "\nBye. Hope to see you again soon!\n" + line;
    }

    public static void echo(){
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();
        if (!sentence.equals("bye")) {
            System.out.println(line + "\n" + sentence + "\n" + line);
            echo();
        }
    }
}
