package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by arjunmenon on 3/22/16.
 */
public class SkillLibrary {
    private ArrayList<Skill> library;
    private ArrayList<Skill> searchResults;
    private ArrayList<Skill> scoreResults;
    private int skillCount;
    private int counter = 0;
    private static String url;
    private static String dbName;
    private static String driver;
    private static String userName;
    private static String password;
    private static Connection conn;

    /**
     * public constructor for a SkillLibrary
     */
    public SkillLibrary(){
        library = new ArrayList<>();
        searchResults = new ArrayList<>();
        scoreResults = new ArrayList<>();
        skillCount = 0;
    }

    /**
     * Initializes the library with the 5 json files and populates the skills list
     */
    public void initLibrary(){
        connectDatabase();
        parseDatabase();
        closeConnection();
    }

    /**
     * Connect to the database
     */
    public void connectDatabase(){
        url = "jdbc:mysql://localhost:3306/";
        dbName = "gymnastics";
        driver = "com.mysql.jdbc.Driver";
        userName = "root";
        password = "";
    }

    public int getSkillCount() {
        return skillCount;
    }

    public ArrayList<Skill> getScoreResults() {
        return scoreResults;
    }

    public ArrayList<Skill> getLibrary() {
        searchResults.clear();
        for(Skill skill : library){
            searchResults.add(skill);
        }
        return library;
    }

    public ArrayList<Skill> getSearchResults() {
        return searchResults;
    }

    /**
     * parses through the MySQL database and adds all the available skills to the skill library
     */
    public void parseDatabase() {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM skills");
            while (res.next()) {
                String skillName = res.getString("skillName");
                String elementGroup = res.getString("elementGroup");
                String letterScore = res.getString("letterScore");
                double score = res.getDouble("score");
                String img_path = res.getString("img_path");
                Skill newSkill = new Skill(skillName, elementGroup, "Still Rings", letterScore, score, img_path);
                library.add(newSkill);
                skillCount++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Close the current database connection
     */
    public void closeConnection(){
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility function that prints all skills in the library
     */
    public void printAllSkills(){
        searchResults.clear();
        counter = 0;
        for (Skill current : library){
            counter++;
            System.out.println(counter+ ") " + current.prettyToString());
            searchResults.add(current);
        }
    }

    /**
     * Print all skills in a given element group
     * @param group
     */
    public void printByElementGroup(String group){
        searchResults.clear();
        counter = 0;
        for (Skill current : library){
            if (current.getElementGroup().equals(group)) {
                counter++;
                System.out.println(counter+ ") " + current.prettyToString());
                searchResults.add(current);
            }
        }
    }

    /**
     * Search the library for a particular skill or similar skills
     * @param searchTerm the query to search for
     */
    public void searchLibrary(String searchTerm){
        searchResults.clear();
        counter = 0;
        for (Skill current : library){
            if(assessSimilarity(searchTerm.toLowerCase(),current.getSkillName().toLowerCase())){
                searchResults.add(current);
            }
        }
        printResults();
    }

    /**
     * Helper function that assess the similarity between two Strings and returns a boolean if the skill is a valid match
     * @param searchTerm the term used to search
     * @param skillName the skill name of the current skill being compared to.
     * @return true for a valid similarity, false otherwise
     */
    private boolean assessSimilarity(String searchTerm, String skillName){
        String[] splitForm = searchTerm.split(" ");
        String stopWords = "to from a";
        for ( String word : splitForm){
            if(skillName.contains(word) && !stopWords.contains(word)) {
                return true;
            }
        }
        if(StringSimilarity.similarity(searchTerm,skillName)>0.6){
            return true;
        }
        return false;
    }

    /**
     * Helper function that prints the output of all searchResult values.
     */
    private void printResults(){
        if(searchResults.isEmpty()){
            System.out.println("No skills found for that search. Try another query.");
        }else{
            for (Skill currentResult : searchResults){
                counter++;
                System.out.println(counter+ ") " + currentResult.prettyToString());
            }
        }
    }

    /**
     * Helper function that will find a skill by its exact name. Will only be used during testing.
     * @param theSkillName skill name parameter to be found
     * @return the Skill object or null if not found
     */
    public Skill findSkillByName(String theSkillName){
        for(Skill current : library){
            if(current.getSkillName().equals(theSkillName)){
                return current;
            }
        }
        return null;
    }

    /**
     * Organize an all skills from the searchResults list that match the letterScore passed in
     * @param letterScore the letterScore to match
     */
    public void organizeByScore(String letterScore){
        scoreResults.clear();
        for (Skill result : searchResults){
            if(result.getLetterScore().equals(letterScore)){
               scoreResults.add(result);
            }
        }
    }




}
