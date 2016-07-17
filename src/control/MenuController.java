package control;
import model.SkillLibrary;
import java.io.File;
import java.awt.Desktop;
import java.io.IOException;
import view.Menu;

/**
 * Created by arjunmenon on 4/5/16.
 */
public class MenuController {
    private static Menu menu = new Menu();
    private static SkillLibrary library = new SkillLibrary();
    private static int value = 0;

    public static void main(String[] args){
        library.parseDatabase();
        runMenu(1);
    }

    /**
     * Run a menu of the selected number from the menu view
     * @param menuNumber the value of the menu that should be run
     */
    public static void runMenu(int menuNumber){
        if(menuNumber == 1){
            value = menu.firstMenu();
            firstMenuSwitch(value);
        }else if(menuNumber == 2){
            value = menu.secondMenu();
            secondMenuSwitch(value);
        }
    }

    /**
     * The switch case control for the first menu from the view
     * @param choice the user input for the choice of the first menu
     */
    public static void firstMenuSwitch(int choice){
        switch (choice){
            case 1:
                library.printAllSkills();
                imageLoader();
                runMenu(1);
                break;
            case 2:
                runMenu(2);
                break;
            case 3:
                library.searchLibrary(menu.searchQuery());
                imageLoader();
                runMenu(1);
                break;
            case 4:
                exit();
                break;
            default:
                System.out.println("Invalid selection.");
                runMenu(1);
                break;
        }
    }

    /**
     * The switch case control for the second menu from the view
     * @param choice the user input for the choice of the second menu
     */
    public static void secondMenuSwitch(int choice){
        switch (choice){
            case 1:
                library.printByElementGroup("Kip and Swing");
                imageLoader();
                runMenu(1);
                break;
            case 2:
                library.printByElementGroup("Swing to Handstand");
                imageLoader();
                runMenu(1);
                break;
            case 3:
                library.printByElementGroup("Swing to Strength");
                imageLoader();
                runMenu(1);
                break;
            case 4:
                library.printByElementGroup("Strength Hold");
                imageLoader();
                runMenu(1);
                break;
            case 5:
                library.printByElementGroup("Dismount");
                imageLoader();
                runMenu(1);
                break;
            case 6:
                runMenu(1);
                break;
        }
    }

    /**
     * Function that asks thew view to ask the user for a skill number to display information
     */
    public static void imageLoader(){
        if(library.getSearchResults().isEmpty()){
            return;
        }
        value = menu.skillPicker();
        if(value==-1){
            runMenu(1);
        }
        if(library.getSearchResults().get(value-1)==null){
            System.out.println("That is an invalid option");
            runMenu(1);
        }else{
            try {
                Desktop desktop = Desktop.getDesktop();
                String file_path = "src/assets/images/" + library.getSearchResults().get(value - 1).getImgPath();
                File file = new File(file_path);
                if (file.exists()) {
                    desktop.open(file);
                }
                runMenu(1);
            } catch (IOException x){
                System.err.println("Some IOException accured (did you set the right path?): ");
            }
        }
    }

    /**
     * Function that is called when the exit option is selected from a menu.
     */
    public static void exit(){
        System.out.println("Exiting...");
        System.exit(1);
    }
}
