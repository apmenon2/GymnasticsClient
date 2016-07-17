package view;

import control.RoutineController;
import model.Skill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by arjunmenon on 4/18/16.
 * Action Listener to control the movement of the skill in a routine
 */
public class SkillMoveAction implements ActionListener {
    private Skill element;
    private RoutineController controller;
    private String direction;

    public SkillMoveAction(Skill theElement, RoutineController control, String direct){
        element = theElement;
        controller = control;
        direction = direct;
    }

    /**
     * On click the direction information is passed on to the controller
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        if(direction.equals("left")) {
            controller.skillLeft(element);
        }else{
            controller.skillRight(element);
        }
    }
}
