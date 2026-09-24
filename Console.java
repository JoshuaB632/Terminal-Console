import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.lang.Thread;

public class Console{
    //Variables
    public static final int NUM_CHARS_PER_LINE = 120;
    public static final int DEFAULT_TIME_PER_CHAR = 5;
    private static final Scanner IN = new Scanner(System.in);
    private static final char MAX_LEVEL = 'E';

    private String currentInterface;
    private boolean powered;

    //TODO: convert Boolean to custom Part class
    private HashMap<String, Boolean> integrityChecks;
    
    //Constructors
    public Console(){
        //Initialize variables
        currentInterface = "constructor";
        powered = false;
        integrityChecks = new HashMap<String, Boolean>();

        //load data
        loadPartIntegrities();
        loadHelpDescriptions();
    }



    //Initializers
    /* creates the map for all system functions and whether they are functional or not.
     */
    private void loadPartIntegrities(){
        //essential (Level A)
        integrityChecks.put("boot", true);
        integrityChecks.put("main_menu", true);
        integrityChecks.put("shutdown", false);

        //important (Level B)
        integrityChecks.put("help", true);
        integrityChecks.put("settings", false);
        integrityChecks.put("system_information", true);
        integrityChecks.put("repair", true);
        integrityChecks.put("reboot", false);

        //basic (Level C)
        integrityChecks.put("query", false);
        integrityChecks.put("logs", false);
        integrityChecks.put("recipes", false);
        integrityChecks.put("disc", false);

        //advanced (Level D)
        integrityChecks.put("map", false);
        integrityChecks.put("scan", false);

        //other (Level E)
        integrityChecks.put("game", true);
    }

    /* reads description of all system functions from file and loads into partsMap.
     */
    private void loadHelpDescriptions(){
        System.out.println("WIP: LOAD_HELP_DESCRIPTIONS");
    }



    //Methods
    //essential methods (Level A)
    public void boot(){
        powered = true;
        currentInterface = "boot";
        System.out.println("WIP: BOOT");
        if(!integrityChecks.get("boot")){
            slowWrite("Failed to run boot...");
            powered = false;
            slowWrite("Exiting...");
            return;
        }

        //check vital stuff (battery inserted, etc)

        /*Brainstorm stuff to check
            -CPU runs BIOS (Basic Input/Output System)
            -POST (Power On Self Test)
                -Self Diagnostics (Memory Remaining, Discs Loaded, etc)
                -Test run basic programs (load Disc info, make/save/delete file, etc)
            -Runs Kernal (self made "OS")
            -Hands input over to user
        */

        //check integrity of all levels
        System.out.println("Integrity Check:");
        for(char level = 'A'; level <= MAX_LEVEL; level++){
            slowWrite("\tLevel " + level + "...", 0);
            sleep(300);
            int integ = checkIntegrity(level);
            printIntegrity(integ);
            if(integ == 0){
                sleep(500);
                String[] comms = getCommandsInLevel(level);
                for(String s : comms){
                    slowWrite("\t\t" + s + "...", 0);
                    sleep(((int) Math.random() * 500) + 1250);
                    printIntegrity((integrityChecks.get(s)) ? 1 : -1);
                }
            }
            sleep(200);
        }

        slowWrite("Boot complete...");
        slowWrite("Loading Main Menu...");
        mainMenu();
    }
    
    public void mainMenu(){
        currentInterface = "main_menu";
        System.out.println("WIP: MAIN_MENU");

        slowWrite("WIP: welcome message, recommend \'help\'");
        while(powered){
            System.out.print("Terminal:> ");
            ArrayList<String> command = split(next(true));
            String input = command.get(0);

			if(input.equals("boot")){
				slowWrite("Boot is a built-in program and cannot be called manually.");
			}
			else if(input.equals("main_menu")){
				slowWrite("Already running main_menu.");
			}
            else if(input.equals("shutdown")){
                if(integrityChecks.get("shutdown")){
                    shutdown();
                }
                else{
                    slowWrite("Program \'shutdown\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }

            else if(input.equals("help")){
                if(integrityChecks.get("help")){
                    if(command.size() >= 2){
                        help(command.get(1));
                    }
                    else{
                        help();
                    }
                }
                else{
                    slowWrite("Program \'help\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }
            else if(input.equals("settings")){
                if(integrityChecks.get("settings")){
                    settings();
                }
                else{
                    slowWrite("Program \'settings\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }
            else if(input.equals("system_information")){
                if(integrityChecks.get("system_information")){
                    system_information();
                }
                else{
                    slowWrite("Program \'system_information\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }
            else if(input.equals("repair")){
                if(integrityChecks.get("repair")){
                    if(command.size() >= 2){
                        repair(command.get(1));
                    }
                    else{
                        slowWrite("Usage: repair [TARGET]");
                        slowWrite("Try \'help repair\' for more information.");
                    }
                }
                else{
                    slowWrite("Program \'repair\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }
            else if(input.equals("reboot")){
                if(integrityChecks.get("reboot")){
                    reboot();
                }
                else{
                    slowWrite("Program \'reboot\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }

            else if(input.equals("query")){
                if(integrityChecks.get("query")){
                    query();
                }
                else{
                    slowWrite("Program \'query\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }
            else if(input.equals("logs")){
                if(integrityChecks.get("logs")){
                    logs();
                }
                else{
                    slowWrite("Program \'logs\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }
            else if(input.equals("recipes")){
                if(integrityChecks.get("recipes")){
                    recipes();
                }
                else{
                    slowWrite("Program \'recipes\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }
            else if(input.equals("disc")){
                if(integrityChecks.get("disc")){
                    disc();
                }
                else{
                    slowWrite("Program \'disc\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }

            else if(input.equals("map")){
                if(integrityChecks.get("map")){
                    map();
                }
                else{
                    slowWrite("Program \'map\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }
            else if(input.equals("scan")){
                if(integrityChecks.get("scan")){
                    scan();
                }
                else{
                    slowWrite("Program \'scan\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }

            else if(input.equals("game")){
                if(integrityChecks.get("game")){
                    game();
                }
                else{
                    slowWrite("Program \'game\' is not stable.");
                    //Eventually add option for user to proceed even if not stable,
                    //  causing either a crash or only partial functionality
                }
            }

            else{
                slowWrite("-bash: " + input + ": command not found");
                //maybe add a counter and if enough unrecognized commands are thrown
                //  then tell the player they can use 'help'
            }
        }
    }

    public void shutdown(){
        System.out.println("WIP: SHUTDOWN");
        //if any unsaved work
            //check if user really wants to shutdown

        //run any code needed to close programs

        powered = false;
    }

    
    //important methods (Level B)
    public void help(){
        System.out.println("HELP UNFINISHED...\n");
        System.out.println("----Commands----");
        System.out.println("Essential (Level A)");
		System.out.println("\tboot");
		System.out.println("\tmain_menu");
        System.out.println("\tshutdown");

        System.out.println("Important (Level B)");
        System.out.println("\thelp");
        System.out.println("\tsettings");
        System.out.println("\tsystem_information");
        System.out.println("\trepair");
        System.out.println("\treboot");

        System.out.println("Basic (Level C)");
        System.out.println("\tquery");
        System.out.println("\tlogs");
        System.out.println("\trecipes");
        System.out.println("\tdisc");

        System.out.println("Advanced (Level D)");
        System.out.println("\tmap");
        System.out.println("\tscan");

        System.out.println("Other (Level E)");
        System.out.println("\tgame");
        System.out.println("---------------");
    }
    public void help(String componentName){
		
	}
    
    public void settings(){
        System.out.println("WIP: SETTINGS");
    }

    public void system_information(){
        System.out.println("WIP: SYSTEM_INFORMATION");

        slowWrite("Stats:");
        slowWrite("\tTesting latency...");
        slowWrite("\t\tWIP");
        slowWrite("\tlatency: 0ms");
        slowWrite("\tTesting memory...");
        slowWrite("\t\tWIP");
        slowWrite("\tmemory: 256MB");
        slowWrite("\tTesting hard drive...");
        slowWrite("\t\tWIP");
        slowWrite("\thard drive: 16GB");
        slowWrite("\tChecking battery...");
        slowWrite("\tbattery: 98%");
        System.out.println();

        slowWrite("Program Structure");
        for(char level = 'A'; level <= MAX_LEVEL; level++){
            slowWrite("\tLevel " + level + "...");
            String[] comms = getCommandsInLevel(level);
            for(String s : comms){
                System.out.print("\t\t" + s + "...");
                printIntegrity((integrityChecks.get(s)) ? 1 : -1);
            }
        }

    }

    public void repair(String componentName){
        System.out.println("WIP: REPAIR");

        // check param is valid
        boolean valid = false;
        for(char level = 'A'; !valid && level <= MAX_LEVEL; level++){
            String[] comms = getCommandsInLevel(level);
            for(String s : comms){
                if(componentName.equals(s)){
                    valid = true;
                    break;
                }
            }
        }
        if(!valid){
            slowWrite("repair: invalid option -- \'" + componentName + "\'");
            slowWrite("Try \'help repair\' for more information.");
            return;
        }

        slowWrite("Attempting to repair \'" + componentName + "\'...");
        System.out.println("\tWIP");
        if(!integrityChecks.get(componentName)){ //Need to make an actual check to see if it can be repaired
            integrityChecks.put(componentName, true);
            slowWrite("\'" + componentName + "\' has been repaired!");
        }
        else{
            slowWrite("\'" + componentName + "\' is already repaired!");
        }
    }

    public void reboot(){
        System.out.println("WIP: REBOOT");
    }


    //Basic (Level C)
    public void query(){
        System.out.println("QUERY UNFINISHED...\nNEEDS REMODELING");

        System.out.println("*insert UNIN logo here*");
        String[] percents = new String[] {"10%", "20%", "47%", "60%", "81%", "87%", "98%", "100%"};
        for(int i = 0; i < percents.length; i++){
            slowWrite("checking file integrity..." + percents[i]);
        }
        slowWrite("running UNIversal_Notes.exe...", 2);

        System.out.print("Welcome to UNIversal Notes! ");
        String input = "";
        do{
            if(input.equals("")){
                slowWrite("Type a word or phrase to look up more information about it. Remember to use underscores ('_') instead of spaces. If you'd like to see all available entries, please enter \'list_entries\'. If you'd like to exit the program, please enter \'back\'.");
            } //TODO: Add more else-if for new info, maybe make it a helper method to look for it in a list
            else if(input.equals("list_entries")){
                slowWrite("Available entries:");
				slowWrite("\ttest");
            }
            else if(input.equals("test")){
                slowWrite("ENTRY NAME:");
                slowWrite("ENTRY INFO");
            }
            else{
                slowWrite("Unfortunately, we do not have the information necessary to inform you about this subject."); //Add a feature to ask if they'd like to contribute to the database
            }
            System.out.println();

            System.out.print("Search Query: ");
            input = next(true);
            System.out.println();
        } while(!(input.equals("b") || input.equals("back")));
    }

    public void logs(){
        System.out.println("WIP: LOGS");
    }

    public void recipes(){
        System.out.println("WIP: RECIPES");
    }

    public void disc(){
        System.out.println("WIP: DISC");
    }


    //Advanced (Level D)
    public void map(){
        System.out.println("WIP: map");
    }

    public void scan(){
        System.out.println("WIP: SCAN");
    }


    //Other (Level E)
    public void game(){
        System.out.println("WIP: GAME");
    }



    //Helpers
    // Returns string array of commands in level
    public static String[] getCommandsInLevel(char level){
        switch(level){
            case 'A':
                return new String[] {"boot", "main_menu", "shutdown"};
            case 'B':
                return new String[] {"help", "settings", "system_information", "repair", "reboot"};
            case 'C':
                return new String[] {"query", "logs", "recipes", "disc"};
            case 'D':
                return new String[] {"map", "scan"};
            case 'E':
                return new String[] {"game"};
            default:
                return null;
        }
    }

    // checks integrity of programs based on level.
    // Returns -1 if none work, 0 if some work, and 1 if all work
    private int checkIntegrity(char level){
        String[] commandsToCheck = getCommandsInLevel(level);
        int count = 0;
        for(String s : commandsToCheck){
            sleep(((int) Math.random() * 1000) + 1500);
            if(integrityChecks.get(s)){
                count++;
            }
        }
        return ((count == 0) ? -1 : ((count == commandsToCheck.length) ? 1 : 0));
    }

    //Prints "good", "partial", or "bad" if input is 1, 0, or -1 respectively
    private static void printIntegrity(int a){
      if(a == -1){
          slowWrite("\u001B[31mbad", 0, 0);
      }
      else if(a == 0){
          slowWrite("\u001B[33mpartial", 0, 0);
      }
      else if(a == 1){
          slowWrite("\u001B[32mgood", 0, 0);
      }
      System.out.println("\u001B[0m");
    }

    // Writes character by character, pausing between characters
    public static String slowWrite(String toWrite, int charTime, int newLine){
        for(;newLine > 0; newLine--){
            toWrite += "\n";
        }

        int indexOfBreak, charsToNewline = NUM_CHARS_PER_LINE;
        for(int i = 0; i < toWrite.length(); i++){
            sleep(charTime);

            //check if current char is newline
            if(toWrite.charAt(i) == '\n'){
                //if so reset charsToNewline
                charsToNewline = NUM_CHARS_PER_LINE;
            }

            //check if current char is a space
            else if(toWrite.charAt(i) == ' '){
                //if it is, calculate distance to next space or newline
                indexOfBreak = toWrite.indexOf(' ', i+1);
                int temp = toWrite.indexOf('\n', i+1);
                indexOfBreak = ((temp != -1 && temp < indexOfBreak) ? temp : indexOfBreak) - i - 1;
                //check if need to make a newline before reaching next space/newline
                if(indexOfBreak > charsToNewline){
                    //if true, skip current char (space) and newline
                    System.out.println();
                    toWrite = toWrite.substring(0, i) + "\n" + toWrite.substring(i+1);
                    charsToNewline = NUM_CHARS_PER_LINE;
                    continue;
                }
            }
        System.out.print(toWrite.charAt(i));
        charsToNewline--;
      }
      return toWrite;
  }
  public static String slowWrite(String toWrite){
      return slowWrite(toWrite, DEFAULT_TIME_PER_CHAR, 1);
  }
  public static String slowWrite(String toWrite, int newLine){
      return slowWrite(toWrite, DEFAULT_TIME_PER_CHAR, newLine);
  }

    // Splits user input by spaces into ArrayList
    public static ArrayList<String> split(String input){
        ArrayList<String> ret = new ArrayList<String>();
        int index = input.indexOf(" ");
        while(index > -1){
            ret.add(input.substring(0, index));
            input = input.substring(index+1);
            index = input.indexOf(" ");
        }
        ret.add(input);
        return ret;
    }

    // Gets user's input, optionally formatting
    public static String next(boolean formatted){
        try{
            String input = IN.nextLine();
            if(!formatted){
                return input;
            }
            else{
                return input.trim().toLowerCase();
            }
        } catch(Exception e) { System.out.println("INVALID INPUT"); return null; }
    }

    // Clears screen
    private static void clear(){
        System.out.print("\033[H\033[2J");
    }

    // Waits x milliseconds (Eventually, replace waits with things that actually take time)
    private static void sleep(long x){
        try{
            Thread.sleep(x);
        } catch(Exception e) { System.out.println("sleep ended early"); }
    }


    
    //Driver
    public static void main(String args[]) {
        //Create Console
        Console c = new Console();

        //Time before using Console
        System.out.print("Press \'Enter\' to mimic using the console.");
        next(false);

        //Use Console
        c.boot();
    }
}