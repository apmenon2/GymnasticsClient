package view;

import control.GuiController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by arjunmenon on 4/10/16.
 * ActionListener to communicate to the controller the search query from the search field
 */
public class SearchPicker implements ActionListener {
    private GuiController controller;
    private JTextField textField;

    /**
     * Constructor for the GuiController and the TextField
     * @param theController the GuiController attached to this view
     * @param theField the JTextField where the query is entered
     */
    public SearchPicker(GuiController theController, JTextField theField){
        textField = theField;
        controller = theController;
    }

    /**
     * On action performed call the controller to manage all query actions and return data to view
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        String text = textField.getText();
        controller.query(text);
    }
}
