package view;

import control.GuiController;
import model.Skill;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by arjunmenon on 4/11/16.
 * Represents the ActionListener for the Library View buttons. When a particular skill is picked, this class will be
 * called.
 */
public class SkillPicker implements ActionListener {
    private Skill skill; // the skill associated with this listener.
    private GuiController controller;

    public SkillPicker(Skill result ,GuiController control){
        skill =  result;
        controller = control;
    }

    /**
     * actionPerformed override method
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        JFrame frame = new JFrame(controller.skillGetName(skill));
        frame.setSize(300,300);
        frame.setLayout(new FlowLayout());
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        try {
            BufferedImage myPicture = ImageIO.read(new File("src/assets/images/"+controller.skillGetImgPath(skill)));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            content.add(picLabel, BorderLayout.CENTER);
        }
        catch (IOException except){}
        addTable(content);
        packAndShow(frame);
    }

    /**
     * Add the table component to the view
     * @param content the content pane associated with the frame
     */
    private void addTable(Container content){
        String[] columnNames = {"Skill Name", "Element Group", "Letter Score", "Score"};
        Object[][] data = {{controller.skillGetName(skill), controller.skillGetElementGroup(skill),
                controller.skillGetLetterScore(skill), controller.skillGetScore(skill)}};
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        content.add(scrollPane, BorderLayout.PAGE_END);
    }

    /**
     * Pack and show frame.
     * @param frame
     */
    private void packAndShow(JFrame frame){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setMinimumSize(frame.getSize());
        frame.pack();
        frame.setVisible(true);
    }
}
