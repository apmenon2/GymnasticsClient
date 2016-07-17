package view;
import java.util.Scanner;

/**
 * Created by arjunmenon on 4/4/16.
 */
public class Menu {
    private Scanner input = new Scanner(System.in);

    /**
     * The first menu that is displayed when program opens
     * @return the user input choice from the first menu
     */
    public int firstMenu(){
        System.out.print("\n--Skill Library--");
        System.out.println(
                "Select an option: \n" +
                "   1) Show all skills\n" +
                "   2) Show element groups\n" +
                "   3) Search for an element\n" +
                "   4) Exit Program\n"
        );
        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }

    /**
     * The second menu that is displayed
     * @return the user input choice from the first menu
     */
    public int secondMenu(){
        System.out.print("\n--Element Groups--");
        System.out.println(
                "Select an option: \n" +
                        "   1) Kip and Swing\n" +
                        "   2) Swing to Handstand\n" +
                        "   3) Swing to Strength\n" +
                        "   4) Strength Hold\n" +
                        "   5) Dismounts\n" +
                        "   6) Back\n"
        );
        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }

    /**
     * Prompts user for a search query
     * @return the query that the user entered.
     */
    public String searchQuery(){
        System.out.println("Enter a skill search:");
        return input.nextLine();
    }

    /**
     * Prompts the user to enter a skill number if available
     * @return the index of the skill that the user wants to explore
     */
    public int skillPicker(){
        System.out.println("Enter a skill number to see more information (enter -1 to cancel):");
        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }

}
