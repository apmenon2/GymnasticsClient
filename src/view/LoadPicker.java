package view;

import control.RoutineController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

/**
 * Created by arjunmenon on 4/26/16.
 * ActionListener Class to deal with the load file action
 */
public class LoadPicker implements ActionListener {
    private RoutineController controller;
    JFileChooser fc; // the file chooser used to load
    JFrame frame; // the parent component of the load

    public LoadPicker(JFrame theFrame, RoutineController control){
        controller = control;
        fc = new JFileChooser();
        frame = theFrame;
    }

    /**
     * If action is performed then open dialog and send file information to controller
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        File workingDirectory = new File(System.getProperty("user.dir")); //sets default to current working directory
        fc.setCurrentDirectory(workingDirectory);
        int returnVal = fc.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            controller.loadRoutine(file); //send file information to controller
        }
    }
}
