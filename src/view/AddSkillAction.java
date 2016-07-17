package view;

import control.RoutineController;
import model.Skill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by arjunmenon on 4/18/16.
 * Action Listener to add a skill to the routine
 */
public class AddSkillAction implements ActionListener {
    private Skill element;
    private RoutineController controller;

    public AddSkillAction(Skill theElement, RoutineController control){
        element = theElement;
        controller = control;
    }

    /**
     * On click call controller to add skill
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        controller.addToRoutine(element);
    }

}
