package view;

import control.GuiController;
import java.util.ArrayList;
import model.Skill;
import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Created by arjunmenon on 4/9/16.
 * Represents the GUI view associated with a Skill Library. Will be controlled by class GuiController
 */
public class LibraryView {
    private JFrame frame;   //frame for view
    private Container content;  //content pane to add all components to
    private JPanel libraryPanel;   //panel for the library view. Only panel that is refreshed
    private JScrollPane scrollPanel;    //Scrollable panel that will hold the library panel
    private JTextField textField;   //textfield for search query entry
    private GuiController controller;   //controller attached to this view. Mostly used to pass actionListeners
    private JMenuBar menuBar;   //JMenuBar item that will hold sorting menu options
    private JMenu sortMenu, scoreSubMenu, groupSubMenu; //All menu items

    /**
     * Constructor that initializes a controller with this view and calls for set up methods
     * @param theController
     */
    public LibraryView(GuiController theController){
        controller = theController;
        frame = new JFrame("Rings Skill Library");
        frame.setSize(1100, 700);
        frame.setLayout(new FlowLayout());
        content = frame.getContentPane();
        libraryPanel = new JPanel(new GridLayout(0, 5, 3, 3)); //Number of rows/columns and spacing between blocks
        scrollPanel = new JScrollPane(libraryPanel);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        searchField();
        setupMenu();
    }

    /**
     * Set up all menus and submenus at the top of the program.
     */
    public void setupMenu(){
        menuBar = new JMenuBar();
        sortMenu = new JMenu("Sort");
        scoreSubMenu = new JMenu("Sort by Score");
        groupSubMenu = new JMenu("Sort by Groups");
        menuBar.add(sortMenu);
        JMenuItem showAllItem = new JMenuItem("Show All Skills");
        showAllItem.addActionListener(new GroupPicker("All", controller));
        menuBar.add(showAllItem);
        setupGroupMenu();
        setupScoreMenu();
        sortMenu.add(groupSubMenu);
        sortMenu.add(scoreSubMenu);
    }

    /**
     * Set up the menu pertaining to sorting by scores
     */
    public void setupScoreMenu() {
        JMenuItem scoreAItem = new JMenuItem("A");
        scoreAItem.addActionListener(new ScorePicker("A", controller));
        JMenuItem scoreBItem = new JMenuItem("B");
        scoreBItem.addActionListener(new ScorePicker("B", controller));
        JMenuItem scoreCItem = new JMenuItem("C");
        scoreCItem.addActionListener(new ScorePicker("C", controller));
        JMenuItem scoreDItem = new JMenuItem("D");
        scoreDItem.addActionListener(new ScorePicker("D", controller));
        JMenuItem scoreEItem = new JMenuItem("E");
        scoreEItem.addActionListener(new ScorePicker("E", controller));
        JMenuItem scoreFItem = new JMenuItem("F");
        scoreFItem.addActionListener(new ScorePicker("F", controller));

        scoreSubMenu.add(scoreAItem);
        scoreSubMenu.add(scoreBItem);
        scoreSubMenu.add(scoreCItem);
        scoreSubMenu.add(scoreDItem);
        scoreSubMenu.add(scoreEItem);
        scoreSubMenu.add(scoreFItem);
    }

    /**
     * Set up the menu pertaining to sorting my element groups
     */
    public void setupGroupMenu() {
        //Add items to groups submenu
        JMenuItem kipSwingItem = new JMenuItem("Kip and Swing");
        kipSwingItem.addActionListener(new GroupPicker("Kip and Swing", controller));
        JMenuItem swingHandstandItem = new JMenuItem("Swing to Handstand");
        swingHandstandItem.addActionListener(new GroupPicker("Swing to Handstand", controller));
        JMenuItem swingStrengthItem = new JMenuItem("Swing to Strength");
        swingStrengthItem.addActionListener(new GroupPicker("Swing to Strength", controller));
        JMenuItem strengthItem = new JMenuItem("Strength Hold");
        strengthItem.addActionListener(new GroupPicker("Strength Hold", controller));
        JMenuItem dismountItem = new JMenuItem("Dismount");
        dismountItem.addActionListener(new GroupPicker("Dismount", controller));

        groupSubMenu.add(kipSwingItem);
        groupSubMenu.add(swingHandstandItem);
        groupSubMenu.add(swingStrengthItem);
        groupSubMenu.add(strengthItem);
        groupSubMenu.add(dismountItem);
    }

    /**
     * Fill the library window with the current query results
     * @param results the list of Skills
     */
    public void populate(ArrayList<Skill> results) {
        libraryPanel.removeAll();
        int width = 200;
        int height = 200;
        //iterate through each result and populateLibrary the grid layout
        for(Skill result : results){
            String path = "src/assets/images/" + result.getImgPath();
            //Use two imageicons to resize the image to fit buttons
            ImageIcon primaryIcon = new ImageIcon(path);
            Image img = primaryIcon.getImage();
            Image resizedImage = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            ImageIcon finalIcon = new ImageIcon(resizedImage);
            //Create and add new button to the grid layout
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(width+10, height+10));
            button.setIcon(finalIcon);
            button.setBorder(new LineBorder(Color.WHITE, 2));
            button.addActionListener(new SkillPicker(result, controller));
            libraryPanel.add(button);
        }
    }

    /**
     * Refresh the view in order to repopulate the panel with new search results.
     */
    public void refreshView() {
        libraryPanel.repaint();
        libraryPanel.revalidate();
    }

    /**
     * Initialize the search field at the top of the view
     */
    public void searchField() {
        textField = new JTextField(20);
        textField.addActionListener(new SearchPicker(controller, textField));
    }

    /**
     * Show the current view. Only called at initial startup of program.
     */
    public void show() {
        Runnable program = new Runnable() {
            @Override
            public void run() {
                content.setLayout(new BorderLayout());
                content.add(textField, BorderLayout.PAGE_START);
                content.add(scrollPanel, BorderLayout.CENTER);
                frame.setJMenuBar(menuBar);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.setMinimumSize(frame.getSize());
                frame.setVisible(true);

            }
        };
        SwingUtilities.invokeLater(program);
    }

}
