package view;

import control.TopController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by arjunmenon on 4/25/16.
 * The main view that opens up with the program
 */
public class TopView {
    private JFrame frame;
    private Container content;
    private TopController controller;

    public TopView(TopController control) {
        controller = control;
        frame = new JFrame("Still Rings King");
        frame.setSize(680,500);
        frame.setLayout(new FlowLayout());
        content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        setupButtons();
    }

    /**
     * Set up the two main buttons on this view (Library and Routine Maker)
     */
    public void setupButtons() {
        JButton libraryButton = new JButton("Library");
        JButton routineButton = new JButton("Routine Builder");
        libraryButton.setPreferredSize(new Dimension(339,470));
        routineButton.setPreferredSize(new Dimension(339,470));
        libraryButton.addActionListener(new ViewPicker("library",controller));
        routineButton.addActionListener(new ViewPicker("routine",controller));

        //create and attach imageicons for either button
        ImageIcon primaryIcon = new ImageIcon("src/assets/left_grip.png");
        Image img = primaryIcon.getImage();
        Image resizedImage = img.getScaledInstance(339, 470, java.awt.Image.SCALE_SMOOTH);
        ImageIcon finalIcon = new ImageIcon(resizedImage);
        routineButton.setIcon(finalIcon);

        primaryIcon = new ImageIcon("src/assets/right_grip.png");
        img = primaryIcon.getImage();
        resizedImage = img.getScaledInstance(339, 470, java.awt.Image.SCALE_SMOOTH);
        finalIcon = new ImageIcon(resizedImage);
        libraryButton.setIcon(finalIcon);

        content.add(libraryButton, BorderLayout.WEST);
        content.add(routineButton, BorderLayout.EAST);
    }

    /**
     * Show function to create and show runnable for this view
     */
    public void show() {
        Runnable program = new Runnable() {
            @Override
            public void run() {
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.setMinimumSize(frame.getSize());
                frame.setVisible(true);

            }
        };
        SwingUtilities.invokeLater(program);
    }
}
