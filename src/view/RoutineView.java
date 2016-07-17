package view;

import control.RoutineController;
import model.Skill;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by arjunmenon on 4/17/16.
 */
@SuppressWarnings("ALL")
public class RoutineView{
    private JFrame frame;   //frame for view
    private Container content;  //content pane to add all components to
    private JPanel libraryPanel;   //panel for the library view. Only panel that is refreshed
    private JPanel routinePanel;
    private JPanel infoPanel;
    private JScrollPane libraryScrollPanel;    //Scrollable panel that will hold the library panel
    private JScrollPane routineScrollPanel;
    private JTextField textField;   //textfield for search query entry
    private RoutineController controller;   //controller attached to this view. Mostly used to pass actionListeners
    private JMenuBar menuBar;   //JMenuBar item that will hold sorting menu options
    private JMenu sortMenu, scoreSubMenu, groupSubMenu, fileSubMenu, toolsSubMenu; //All menu items


    public RoutineView(RoutineController rController) {
        controller = rController;
        frame = new JFrame("Rings Skill Library");
        frame.setSize(1250, 700);
        frame.setLayout(new FlowLayout());
        content = frame.getContentPane();
        setupPanels();
        searchField();
        setupMenu();
    }

    /**
     * Helper function to the constructor to set up all the panels in the view
     */
    private void setupPanels(){
        libraryPanel = new JPanel(new GridLayout(0, 1, 3, 3)); //Number of rows/columns and spacing between blocks
        libraryPanel.setSize(450, 600);
        routinePanel = new JPanel(new GridLayout(0, 3, 3, 3));
        routinePanel.setSize(650, 600);
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setPreferredSize(new Dimension(650,100));
        libraryScrollPanel = new JScrollPane(libraryPanel);
        libraryScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        routineScrollPanel = new JScrollPane(routinePanel);
        routineScrollPanel.setSize(650,600);
        routineScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Set up all menus and submenus at the top of the program.
     */
    private void setupMenu() {
        menuBar = new JMenuBar();
        sortMenu = new JMenu("Filter");
        scoreSubMenu = new JMenu("Filter by Score");
        groupSubMenu = new JMenu("Filter by Groups");
        toolsSubMenu = new JMenu("Tools");
        fileSubMenu = new JMenu("File");
        menuBar.add(fileSubMenu);
        menuBar.add(sortMenu);
        menuBar.add(toolsSubMenu);
        setupGroupMenu();
        setupScoreMenu();
        setupFileMenu();
        setupToolsMenu();
        sortMenu.add(groupSubMenu);
        sortMenu.add(scoreSubMenu);
    }

    /**
     * Set up submenu for file options (save, load)
     */
    private void setupFileMenu() {
        JMenuItem saveItem = new JMenuItem("Save Routine");
        JMenuItem loadItem = new JMenuItem("Load Routine");
        saveItem.addActionListener(new SavePicker(frame, controller));
        loadItem.addActionListener(new LoadPicker(frame, controller));
        fileSubMenu.add(saveItem);
        fileSubMenu.add(loadItem);
    }

    /**
     * Set up submenu for tools (all skills, score)
     */
    private void setupToolsMenu() {
        JMenuItem showAllItem = new JMenuItem("Show All Skills");
        showAllItem.addActionListener(new GroupPicker("All", controller));
        JMenuItem infoItem = new JMenuItem("Score and Validate");
        infoItem.addActionListener(new InfoPicker(controller));
        toolsSubMenu.add(showAllItem);
        toolsSubMenu.add(infoItem);
    }

    /**
     * Set up the menu pertaining to sorting by scores
     */
    private void setupScoreMenu() {
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
    private void setupGroupMenu() {
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
    public void populateLibrary(ArrayList<Skill> results) {
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
            button.addActionListener(new AddSkillAction(result, controller));
            libraryPanel.add(button);
        }
    }

    /**
     * Populate the routinePanel with elements
     * @param routine the current routine as passed in by the controller
     */
    public void populateRoutine(ArrayList<Skill> routine){
        routinePanel.removeAll();
        int width = 200;
        int height = 200;
        for(Skill element : routine){
            JPanel elementPanel = returnPanel(element);
            String path = "src/assets/images/" + element.getImgPath();
            //Use two imageicons to resize the image to fit buttons
            ImageIcon primaryIcon = new ImageIcon(path);
            Image img = primaryIcon.getImage();
            Image resizedImage = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            ImageIcon finalIcon = new ImageIcon(resizedImage);
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(width+10, height+10));
            button.setIcon(finalIcon);
            button.addActionListener(new SkillPicker(element, controller));
            elementPanel.add(button,BorderLayout.CENTER);
            routinePanel.add(elementPanel);
        }
    }

    /**
     * Create the button group around the elements in the routine
     * @param element the skill element to create buttons around
     * @return the panel of buttons
     */
    private JPanel returnPanel(Skill element){
        JPanel elementPanel = new JPanel(new BorderLayout());// the panel that will contain skill and associated buttons
        elementPanel.setSize(210,205);
        elementPanel.setBorder(new LineBorder(Color.PINK,3));
        JButton rightButton = new JButton(">");
        JButton leftButton = new JButton("<");
        JButton deleteButton = new JButton("Delete");
        rightButton.setPreferredSize(new Dimension(40, 200));
        leftButton.setPreferredSize(new Dimension(40,200));
        deleteButton.setSize(200, 5);
        rightButton.addActionListener(new SkillMoveAction(element,controller,"right"));
        leftButton.addActionListener(new SkillMoveAction(element,controller,"left"));
        deleteButton.addActionListener(new RemoveSkillAction(element, controller));
        elementPanel.add(rightButton,BorderLayout.EAST);
        elementPanel.add(leftButton,BorderLayout.WEST);
        elementPanel.add(deleteButton,BorderLayout.SOUTH);
        return elementPanel;
    }


    /**
     * Paint the error and score info
     * @param errors
     * @param start
     */
    public void paintInfo(ArrayList<String> errors, double start){
        infoPanel.removeAll();
        JTextArea textArea = new JTextArea();
        String toPrint = new String();
        toPrint = "Start Value: " + start + "\n";
        for (String error : errors){
            toPrint += error + "\n";
        }
        textArea.setText(toPrint);
        infoPanel.add(textArea);
    }

    /**
     * Refresh the view in order to repopulate the panel with new search results.
     */
    public void libraryRefreshView() {
        libraryPanel.repaint();
        libraryPanel.revalidate();
    }

    /**
     * Refresh the routine panel view to repopulate the panel with new routine elements
     */
    public void routineRefreshView(){
        routinePanel.repaint();
        routinePanel.revalidate();
    }

    /**
     * Refresh the info view
     */
    public void infoRefreshView(){
        infoPanel.repaint();
        infoPanel.revalidate();
    }

    /**
     * Initialize the search field at the top of the view
     */
    public void searchField() {
        textField = new JTextField(20);
        textField.addActionListener(new SearchPicker(controller, textField));
    }

    public void badFile(){
        JOptionPane.showMessageDialog(frame,
                "That is not a valid file",
                "Bad File",
                JOptionPane.ERROR_MESSAGE);
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
                content.add(libraryScrollPanel, BorderLayout.EAST);
                content.add(routineScrollPanel, BorderLayout.CENTER);
                content.add(infoPanel, BorderLayout.SOUTH);
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
