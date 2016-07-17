package view;

import control.RoutineController;
import model.Skill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by arjunmenon on 4/18/16.
 * Action Listener for the delete button associated with a skill in the routine
 */
public class RemoveSkillAction implements ActionListener {
    private Skill element;
    private RoutineController controller;

    public RemoveSkillAction(Skill theElement, RoutineController control){
        element = theElement;
        controller = control;
    }

    /**
     * On click, call controller to remove the skill
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        controller.removeFromRoutine(element);
    }
}