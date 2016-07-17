package model;
import java.util.ArrayList;

/**
 * Created by arjunmenon on 3/22/16.
 */
public class Skill {
    private String skillName;
    private ArrayList<String> alternateNames = new ArrayList<>();
    private String elementGroup;
    private int elementGroupID;
    private String event;
    private String imgPath;
    private String letterScore;
    private double score;

    public Skill(String theSkillName, String theElementGroup, String theEvent, String theLetterScore, double theScore, String thePath){
        this.skillName = theSkillName;
        this.elementGroup = theElementGroup;
        this.event = theEvent;
        this.letterScore = theLetterScore;
        this.score = theScore;
        this.imgPath = thePath;
        this.elementGroupID = getIDFromGroup(elementGroup);
    }

    public String getSkillName() {
        return skillName;
    }

    public String getElementGroup() {
        return elementGroup;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public double getScore() {
        return score;
    }

    public String getLetterScore() {
        return letterScore;
    }

    public ArrayList<String> getAlternateNames() {
        return alternateNames;
    }


    public int getElementGroupID() {
        return elementGroupID;
    }

    /**
     * Adds a new alternate name to the list of alternate names
     * @param alternateName the name to add to the list
     */
    public void addAlternateName(String alternateName){
        this.alternateNames.add(alternateName);
    }

    /**
     * toString method for the Skill object. Auto-generated and will only be used for testing.
     * @return
     */
    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + skillName + '\'' +
                ", alternateNames=" + alternateNames +
                ", elementGroup='" + elementGroup + '\'' +
                ", event='" + event + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", letterScore='" + letterScore + '\'' +
                ", score=" + score +
                '}';
    }

    /**
     * Custom toString method which can be used to print out basic Skill information
     * @return pretty version of Skill information
     */
    public String prettyToString(){
        return skillName + "\n"
                + "Score: " + letterScore + " (" + score + ") \n"
                + "Group: " + elementGroup+ "\n";
    }

    /**
     * Calculate the numerical value of the element Group from the name of the element group.
     * @param group the String value of the element group
     * @return the numerical value of the element group
     */
    private int getIDFromGroup(String group) {
        if (group.equals("Kip and Swing")) {
            return 1;
        } else if (group.equals("Swing to Handstand")) {
            return 2;
        } else if (group.equals("Swing to Strength")) {
            return 3;
        } else if (group.equals("Strength Hold")) {
            return 4;
        } else{
            return 5;
        }
    }

    /**
     * Checks whether or not this Skill is a Strength Element sill (group 3,4)
     * @return true if this element is a strength element. False otherwise
     */
    public boolean isStrengthElement(){
        if(elementGroupID==3 || elementGroupID==4){
            return true;
        }
        return false;
    }

    /**
     * Checks whether or not this Skill is a Swing Element sill (group 1,2)
     * @return true if this element is a swing element. False otherwise
     */
    public boolean isSwingElement(){
        if(elementGroupID==1 || elementGroupID==2){
            return true;
        }
        return false;
    }
}
