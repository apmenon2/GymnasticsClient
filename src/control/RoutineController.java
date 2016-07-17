package control;

import view.RoutineView;
import model.SkillLibrary;
import model.Skill;
import model.Routine;

import java.io.*;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by arjunmenon on 4/17/16.
 */
public class RoutineController extends GuiController{
    private SkillLibrary modelLibrary; // model
    private RoutineView routineView; // view
    private Routine routine; //model

    public RoutineController(){
        modelLibrary = new SkillLibrary();
        modelLibrary.initLibrary();
        routineView = new RoutineView(this);
        routine = new Routine("Routine 1", "SR");
    }

    /**
     * Show the entire library with all skills and communicate to view
     */
    public void fullLibraryView() {
        routineView.populateLibrary(modelLibrary.getLibrary());
        routineView.libraryRefreshView();
    }

    /**
     * Show a parameter by a particular element group
     * @param group the group to filter by
     */
    public void elementGroupView(String group){
        modelLibrary.printByElementGroup(group);
        routineView.populateLibrary(modelLibrary.getSearchResults());
        routineView.libraryRefreshView();
    }

    /**
     * Show the runnable view
     */
    public void showView(){
        routineView.show();
    }

    /**
     * Search the query in the library model and pass in the resutling arraylist to the view
     * @param searchQuery
     */
    public void query(String searchQuery){
        modelLibrary.searchLibrary(searchQuery);
        routineView.populateLibrary(modelLibrary.getSearchResults());
        routineView.libraryRefreshView();
    }

    /**
     * Organize the current view by score
     * @param score the score to organize by
     */
    public void scoreView(String score){
        modelLibrary.organizeByScore(score);
        routineView.populateLibrary(modelLibrary.getScoreResults());
        routineView.libraryRefreshView();
    }

    /**
     * Add a skill to the routine
     * @param skillToAdd the skill that needs to be added to the routine
     */
    public void addToRoutine(Skill skillToAdd) {
        routine.addSkill(skillToAdd);
        routineView.populateRoutine(routine.getRoutine());
        routineView.routineRefreshView();
    }

    /**
     * Remove a skill from the routine
     * @param skillToRemove the skill that needs to be removed from the routine
     */
    public void removeFromRoutine(Skill skillToRemove) {
        routine.removeSkill(skillToRemove);
        routineView.populateRoutine(routine.getRoutine());
        routineView.routineRefreshView();
    }

    /**
     * Move a skill to the left (up)
     * @param skill the skill in the routine to move up
     */
    public void skillLeft(Skill skill){
        routine.skillUp(skill);
        routineView.populateRoutine(routine.getRoutine());
        routineView.routineRefreshView();
    }

    /**
     * move a skill to the right (down)
     * @param skill the skill in the routine to move down
     */
    public void skillRight(Skill skill){
        routine.skillDown(skill);
        routineView.populateRoutine(routine.getRoutine());
        routineView.routineRefreshView();
    }

    /**
     * Validate and score the current routine
     */
    public void validateAndScore(){
        routine.validateRoutine();
        routine.scoreRoutine();
        routineView.paintInfo(routine.getErrors(), routine.getStartValue());
        routineView.infoRefreshView();
    }

    public void saveRoutine(File file){
        JSONObject object = new JSONObject();
        object.put("Name", routine.getRoutineName());
        object.put("Event", "Still Rings");

        JSONArray routineArray = new JSONArray();
        for(Skill element : routine.getRoutine()){
            routineArray.add(element.getSkillName());
        }

        object.put("Skill List", routineArray);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(object.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + object);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadRoutine(File file){
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj;
            if(jsonObject.get("Event")==null){
                routineView.badFile();
            }
            String newName = (String) jsonObject.get("Name");
            String newEvent = (String) jsonObject.get("Event");
            routine = new Routine(newName, newEvent);
            JSONArray skillList = (JSONArray) jsonObject.get("Skill List");
            Iterator skillIterator = skillList.iterator();
            while (skillIterator.hasNext()) {
                String skill = (String) skillIterator.next();
                routine.addSkill(modelLibrary.findSkillByName(skill));
            }
            routineView.populateRoutine(routine.getRoutine());
            routineView.routineRefreshView();
        } catch (Exception e){
            routineView.badFile();
            e.printStackTrace();
        }
    }


}
