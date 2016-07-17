package control;

import view.TopView;

/**
 * Created by arjunmenon on 4/25/16.
 * Controller for the top level view in the program
 */
public class TopController {
    private TopView view;
    private RoutineController routineController;
    private GuiController guiController;

    /**
     * Initialize the view and the 2 sub controllers needed
     */
    public TopController() {
        view = new TopView(this);
        routineController = new RoutineController();
        guiController = new GuiController();
    }

    /**
     * Start the library controller and view
     */
    public void startLibrary() {
        guiController.fullLibraryView();
        guiController.showView();
    }

    /**
     * Start the routine maker controller and view
     */
    public void startRoutine() {
        routineController.fullLibraryView();
        routineController.showView();
    }

    /**
     * Show the top level view
     */
    public void showView() {
        view.show();
    }
}
