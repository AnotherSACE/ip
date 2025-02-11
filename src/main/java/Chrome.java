public class Chrome {

    static String line = "____________________________________________________________\n";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(greet());
        System.out.println(exit());
    }

    public static String greet(){
        return line + "Hello! I'm Chrome \n" +
                "What can I do for you?";
    }

    public static String exit(){
        return line + "Bye. Hope to see you again soon!\n" + line;
    }
}
