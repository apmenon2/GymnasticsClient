package view;

import control.TopController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by arjunmenon on 4/26/16.
 */
public class ViewPicker implements ActionListener {
    private TopController controller;
    private String viewType;

    public ViewPicker(String type, TopController control){
        viewType = type;
        controller = control;
    }

    public void actionPerformed(ActionEvent e){
        if(viewType.equals("library")){
            controller.startLibrary();
        }else{
            controller.startRoutine();
        }
    }
}
