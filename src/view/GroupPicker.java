package view;

import control.GuiController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by arjunmenon on 4/10/16.
 * ActionListener Class to communicate to the controller the element group that has been selected to sort by
 */
public class GroupPicker implements ActionListener {
    GuiController controller;
    String groupName;

    /**
     * Constructor assigns controller and groupname
     * @param filter the group to sort by
     * @param control the controller for the program
     */
    public GroupPicker(String filter, GuiController control){
        controller = control;
        groupName = filter;
    }

    /**
     * On action perform, check whether the group is all or not. If all then show all elements from controller.
     * Otherwise only print the group subsection
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        if(groupName.equals("All")){
            controller.fullLibraryView();
        }else {
            controller.elementGroupView(groupName);
        }
    }
}
