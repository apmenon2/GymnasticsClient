package model;

import java.util.ArrayList;

/**
 * Created by arjunmenon on 4/4/16.
 * Object library that represents a Gymnastics Rings routine.
 */
public class Routine {
    private String event;
    private String routineName;
    private int numElements;
    private double scoreDeduction;
    private double startValue;
    private ArrayList<Skill> routine;
    private ArrayList<String> errors;
    private double[] deductions;

    public Routine(String name, String theEvent){
        routine = new ArrayList<>();
        errors = new ArrayList<>();
        event = theEvent;
        routineName = name;
        numElements = 0;
        scoreDeduction = 0;
        startValue = 10.0;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public int getNumElements() {
        return numElements;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public double getStartValue() {
        return startValue;
    }


    public ArrayList<Skill> getRoutine() {
        return routine;
    }

    /**
     * Adds a new skill to the routine if it does not already exist in it.
     * @param newSkill the new skill to add
     * @return true if added successfully, false otherwise
     */
    public boolean addSkill(Skill newSkill) {
        if(!containsSkill(newSkill)) {
            routine.add(newSkill);
            numElements++;
            startValue+=newSkill.getScore();
            return true;
        }
        return false;
    }

    /**
     * Removes a skill from the routine if it contains it.
     * @param removeSkill skill to be removed
     * @return true if successful, false otherwise
     */
    public int removeSkill(Skill removeSkill){
        if(routine.isEmpty()){
            return -1;
        }else {
            for (Skill current : routine) {
                if (current.getSkillName().equals(removeSkill.getSkillName())) {
                    routine.remove(current);
                    numElements--;
                    startValue-=current.getScore();
                    return 1;
                }
            }
            return 0;
        }
    }

    /**
     * Checks to see if the given skill is in the routine
     * @param checkContains skill to be checked for presence
     * @return true if the skill is contained, false otherwise
     */
    public boolean containsSkill(Skill checkContains){
        if(routine.isEmpty()){
            return false;
        }else{
            for(Skill current : routine){
                if(current.getSkillName().equals(checkContains.getSkillName())){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Validates the routine and adds any errors to the error list.
     */
    public void validateRoutine(){
        scoreDeduction = 0;
        errors.clear();
        deductions = new double[routine.size()];
        checkAllGroups();
        checkNumSkills();
        checkDismount();
        checkDismountIsLast();
        checkNotTooManyStrength();
        checkSwingAfterStrength();

        if(!errors.isEmpty()){
            for (String currError : errors){
                System.out.println(currError);
            }
        }
    }

    /**
     * Scores the routine and updates the start value.
     */
    public void scoreRoutine(){
        startValue = 12.5;
        double[] scoredElements = new double[routine.size()];

        findTopTen(scoredElements);
        aggregateScore(scoredElements);
        stickBonus();

        startValue-=scoreDeduction;
        System.out.printf("%.3f",startValue);
    }

    /**
     * Checks to see if the routine contains all element groups
     * @return true if all element groups contained, false otherwise
     */
    public boolean checkAllGroups(){
        boolean[] groups = new boolean[5];
        for(Skill element : routine){
            groups[element.getElementGroupID()-1] = true;
        }
        int deductions = 0;
        for (boolean group : groups) {
            if(!group){
                deductions++;
            }
        }
        scoreDeduction += (0.5 * deductions);
        for (boolean group : groups){
            if(!group) {
                errors.add("Not all Element Groups are satisfied");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see whether the routine has a proper number of skills.
     * @return true if at least 7 skills, false otherwise
     */
    public boolean checkNumSkills(){
        if(routine.size()<7){
            scoreDeduction += (7-routine.size())*0.5;
            errors.add("Insufficient Number of Skills");
            return false;
        }
        return true;
    }

    /**
     * Checks to see whether a dismount exists
     * @return true if exactly one dismount exists. False if none or more than one dismount exist
     */
    public boolean checkDismount(){
        int dismountCount = 0;
        for(Skill current : routine){
            if (current.getElementGroupID()==5){
                dismountCount++;
            }
        }
        if(dismountCount==0){
            errors.add("No dismount found");
            return false;
        }else if(dismountCount>1){
            errors.add("More than one dismount exists");
            return false;
        }else {
            return true;
        }
    }

    /**
     * Checks to see whether the dismount is last in the routine
     * @return true if the dismount is last, false otherwise
     */
    public boolean checkDismountIsLast(){
        if(routine.get(routine.size()-1).getElementGroupID()!=5){
            errors.add("Last skill must be a dismount");
            return false;
        }
        return true;
    }

    /**
     * Checks to see whether there is a sequence of too many strength elements
     * @return true if there is a sequence for more than 3 strength elements, false otherwise
     */
    public boolean checkNotTooManyStrength(){
        int index;
        for(Skill current : routine){
            index = routine.indexOf(current);
            if(current.isStrengthElement()){
                if((index+3)<(routine.size()-1)){
                    if(routine.get(index+1).isStrengthElement() && routine.get(index+2).isStrengthElement() && routine.get(index+3).isStrengthElement()){
                        int currIndex = index+3;
                        double deductionValue = 0;
                        while(routine.get(currIndex)!=null && routine.get(currIndex).isStrengthElement()){
                            deductions[currIndex]=routine.get(currIndex).getScore();
                            currIndex++;
                        }
                        errors.add("Too many strength elements in a sequence");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Check to see whether a proper swing skill separates strength sequences
     * @return true if strength sequences are properly separated, false otherwise
     */
    public boolean checkSwingAfterStrength(){
        int index;
        for(Skill current : routine) {
            index = routine.indexOf(current);
            if (current.isStrengthElement()) {
                if ((index + 4) < (routine.size() - 1)) {
                    if (routine.get(index + 1).isStrengthElement() && routine.get(index + 2).isStrengthElement()
                            && routine.get(index + 4).isStrengthElement()) {
                        if(!(routine.get(index+3).getElementGroupID()==1 || routine.get(index+3).getElementGroupID()==2)
                                || (routine.get(index+3).getScore()<0.2)){
                            if(routine.get(index+3).getScore()<0.2){
                                deductions[index+3]=0.1;
                            }
                            errors.add("B swing is required between strength sequences");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Finds the top ten best elements in the routine to add to the score
     * @param scoredElements the array of double that is passed in to keep track of elements which are scored
     */
    public void findTopTen(double[] scoredElements){
        int counter = 0;
        while(counter!=10 && counter!=routine.size()){
            double currMax = 0.0;
            int currMaxIndex = 0;
            for(int i=0; i<routine.size(); i++){
                if(routine.get(i).getScore()>currMax && scoredElements[i]==0.0){
                    currMax = routine.get(i).getScore();
                    currMaxIndex = i;
                }
            }
            scoredElements[currMaxIndex]=routine.get(currMaxIndex).getScore();
            counter++;
        }
    }

    /**
     * Uses the double array of scored elements to calculate score values and add them to the starting value
     * @param scoredElements double array of scored elements
     */
    public void aggregateScore(double[] scoredElements){
        for(int i=0; i<scoredElements.length; i++){
            if(scoredElements[i]!=0.0){
                //System.out.println(startValue + " " + scoredElements[i]);
                startValue+=scoredElements[i];
                startValue-=deductions[i];
            }
        }
    }

    /**
     * Calculates the stick bonus (if applicable) for a dismount. Also calculates
     * whether a dismount requirement deduction is in need
     * @return the value of the stick bonus if applicable.
     */
    public double stickBonus(){
        if(checkDismountIsLast()){
            //Determine if the dismount meets requirement.
            if(routine.get(routine.size()-1).getScore()<0.4){
                scoreDeduction+=0.2;
            }
            //Determine if and what the stick bonus should be.
            if(routine.get(routine.size()-1).getScore()==0.2){
                startValue+=0.1;
                return 0.1;
            }else if(routine.get(routine.size()-1).getScore()>0.2){
                startValue+=0.2;
                return 0.2;
            }
        }
        return 0.0;
    }

    /**
     * Move a skill up in the order (swap with skill before it)
     * @param skill the skill to move up
     */
    public void skillUp(Skill skill){
        int currIndex = routine.indexOf(skill);
        if(currIndex == -1 || currIndex == 0){
            return;
        }
        this.removeSkill(skill);
        routine.add(currIndex-1,skill);
    }

    /**
     * Move a skill down in the order (swap with skill after it)
     * @param skill the skill to move down
     */
    public void skillDown(Skill skill){
        int currIndex = routine.indexOf(skill);
        if(currIndex == -1 || currIndex == routine.size()-1){
            return;
        }
        this.removeSkill(skill);
        routine.add(currIndex+1,skill);
    }
}
