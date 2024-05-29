import java.util.*;
class ConsoleTextEditor
{   
    private static String clipboard="";
    private static String Display[]={};
    private static ArrayList<String>Show=new ArrayList<>(Arrays.asList(Display));
    private static Scanner in = new Scanner(System.in);
    public static void main(String args[])
    {
        System.out.println("\033[1m--Welcome to Console Text Editor!--\033[0m");
        Instructions();
        boolean running=true;
        while(running)
        {
            System.out.print(">");
            String cmd = in.nextLine();
            running=executecommand(cmd);
        }
        System.out.println("\033[3m\033[1mExiting Console Text Editor. Goodbye!! \033[0m");
    }
    protected static void Instructions() 
    {
        System.out.println("\033[3m\033[1mCommands:\033[0m");
        System.out.println("\033[3m   type <text>     - Add text to the Document evertime in the next Line  ");
        System.out.println("   view            - View the current text Document  ");
        System.out.println("   copy <line>     - Copy the specified line to clipboard  ");
        System.out.println("   cut <line>      - Cut the specified line to clipboard  ");
        System.out.println("   paste <line>    - Paste clipboard content after the specified line  ");
        System.out.println("   delete <line>   - Delete the specified line  ");
        System.out.println("   exit            - Exit the text editor\033[0m  ");
    }
    public static boolean executecommand(String cmd)
    {
        Boolean bol = true;
        cmd.trim();//Don't fool around with White Spaces
        String temp[] = cmd.split(" ",2);//Splitting the text in variable cmd into two parts : before space and after space
        String action = temp[0];//checking for a command
        switch(action)
        {
            case "type":
            if(temp[1].length()>2)
            Show.add(temp[1].substring(1,(temp[1].length()-1)).concat("\n"));
            else
            System.out.println("Error : Text not provided");
            break;
            
            case "view":
            printer();
            break;
            
            
            case "copy":
            if (temp[1].length() > 2) {
                int line = Integer.parseInt(temp[1].substring(1,temp[1].length()-1)) - 1;
                copyLine(line);
            } else {
                System.out.println("Error: No line number provided.");
            }
            break;
            
            case "paste":
            if (temp[1].length() > 2) {
                int line = Integer.parseInt(temp[1].substring(1,temp[1].length()-1));
                pasteLine(line);
            } else {
                System.out.println("Error: No line number provided.");
            }
            break;
            
            case "cut":
            if (temp[1].length() > 2) {
                int line = Integer.parseInt(temp[1].substring(1,temp[1].length()-1));
                cutLine(line);
            } else {
                System.out.println("Error: No line number provided.");
            }
            break;
            
            case "delete":
            if (temp[1].length() > 2) {
                int line = Integer.parseInt(temp[1].substring(1,temp[1].length()-1));
                deleteLine(line);
            } else {
                System.out.println("Error: No line number provided.");
            }
            break;
            
            case "exit":
            bol=false;
            break;
            
            default:
            System.out.println("Error : Invalid Command");
        }
        return(bol);
    }
    protected static void printer()
    {
        Display = Show.toArray(new String[0]);
        System.out.println();
        System.out.println("\033[2m-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X- \033[1m\033[3mDocument \033[2m-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-\033[0m\n");
        for(int i=0;i<Display.length;i++)
        {
            System.out.print(Display[i]);
        }
        System.out.println("\033[2m\nX-X-X-X-X-X-X-X-X-X-X-X-X-X- \033[1m\033[3mDocument Ended\033[2m -X-X-X-X-X-X-X-X-X-X-X-X-X-X\033[0m");
        System.out.println();
    }
    protected static void copyLine(int a)
    {
        Display = Show.toArray(new String[0]);
        clipboard=Display[a];
        System.out.println("Copied Line number " + (a+1));
    }
    protected static void pasteLine(int a)
    {
        Show.add(a,clipboard);
        clipboard="";
        System.out.println("Pasted the text after line " + a + " so the pasted text is Line " + (a+1));
    }
    protected static void cutLine(int a)
    {
        Display=Show.toArray(new String[0]);
        clipboard=Display[a-1];
        Display[a-1]="";
        Show=new ArrayList<>(Arrays.asList(Display));
        System.out.println("Line " + a +" has been cut to clipboard");
    }
    protected static void deleteLine(int a)
    {
        Display = Show.toArray(new String[0]);
        Display[a-1] = "";
        Show = new ArrayList<>(Arrays.asList(Display));
        System.out.println("Line "+a+" has been deleted but keep in mind that the numbering of lines in the document is still the same");
    }
}