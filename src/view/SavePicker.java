package view;

import control.RoutineController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

/**
 * Created by arjunmenon on 4/26/16.
 *  ActionListener Class to deal with the load file action
 */
public class SavePicker implements ActionListener {
    private RoutineController controller;
    JFileChooser fc; // file chooser used for saving
    JFrame frame;

    public SavePicker(JFrame theFrame, RoutineController control){
        controller = control;
        fc = new JFileChooser();
        frame = theFrame;
    }

    /**
     * Shows dialog for saving a file and sends information to controller
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        File workingDirectory = new File(System.getProperty("user.dir")); // set current working directory
        fc.setCurrentDirectory(workingDirectory);
        int returnVal = fc.showSaveDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            controller.saveRoutine(file); // if file chosen then send info to controller
        }
    }
}
