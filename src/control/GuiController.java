package control;

import view.RoutineView;
import model.SkillLibrary;
import model.Skill;
import view.LibraryView;

/**
 * Created by arjunmenon on 4/9/16.
 * Controller class for the GUI view of the skill library. View: LibraryView. Model:SkillLibrary
 */
public class GuiController {
    private SkillLibrary modelLibrary;
    private LibraryView libraryView;

    public GuiController(){
        modelLibrary = new SkillLibrary();
        modelLibrary.initLibrary();
        libraryView = new LibraryView(this);
    }

    /**
     * Show the entire library with all skills and communicate to view
     */
    public void fullLibraryView() {
        libraryView.populate(modelLibrary.getLibrary());
        libraryView.refreshView();
    }

    /**
     * Show a parameter by a particular element group
     * @param group the group to filter by
     */
    public void elementGroupView(String group){
        modelLibrary.printByElementGroup(group);
        libraryView.populate(modelLibrary.getSearchResults());
        libraryView.refreshView();
    }

    /**
     * Show the runnable view
     */
    public void showView(){
        libraryView.show();
    }

    /**
     * Search the query in the library model and pass in the resutling arraylist to the view
     * @param searchQuery
     */
    public void query(String searchQuery){
        modelLibrary.searchLibrary(searchQuery);
        libraryView.populate(modelLibrary.getSearchResults());
        libraryView.refreshView();
    }

    /**
     * Organize the current view by score
     * @param score the score to organize by
     */
    public void scoreView(String score){
        modelLibrary.organizeByScore(score);
        libraryView.populate(modelLibrary.getScoreResults());
        libraryView.refreshView();
    }

    public String skillGetName(Skill skill){
        return skill.getSkillName();
    }

    public String skillGetElementGroup(Skill skill){
        return skill.getElementGroup();
    }

    public String skillGetLetterScore(Skill skill){
        return skill.getLetterScore();
    }

    public double skillGetScore(Skill skill){
        return skill.getScore();
    }

    public String skillGetImgPath(Skill skill){
        return skill.getImgPath();
    }

}
