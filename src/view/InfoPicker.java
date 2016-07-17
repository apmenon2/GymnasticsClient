package view;

import control.RoutineController;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

/**
 * Created by arjunmenon on 4/19/16.
 */
public class InfoPicker implements ActionListener {
    RoutineController controller;

    public InfoPicker(RoutineController control){
        controller = control;
    }

    public void actionPerformed(ActionEvent e){
        controller.validateAndScore();
    }
}
