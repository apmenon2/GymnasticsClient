package view;

import control.GuiController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by arjunmenon on 4/11/16.\
 * Class that represented
 */
public class ScorePicker implements ActionListener {
    private GuiController controller;
    private String score;

    /**
     * Constructor that sets up the score and controller for the GUI
     * @param filter
     * @param control
     */
    public ScorePicker(String filter, GuiController control){
        controller = control;
        score = filter;
    }

    /**
     * controller method is called
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        controller.scoreView(score);
    }
}
