
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.sql.*;
import java.util.Iterator;

/**
 * Created by arjunmenon on 4/25/16.
 * Class used to populate MySQL database with skill information.
 */
public class Database {
    //variables used for databse connection
    private static String url;
    private static String dbName;
    private static String driver;
    private static String userName;
    private static String password;
    private static Connection conn;

    /**
     * opens connection, parses data, and closes connection
     * @param args
     */
    public static void main(String[] args){
        connectDatabse();
        parseJsonToDatabase("src/assets/dismounts.json");
        parseJsonToDatabase("src/assets/kipAndSwing.json");
        parseJsonToDatabase("src/assets/strength.json");
        parseJsonToDatabase("src/assets/swingToHandstand.json");
        parseJsonToDatabase("src/assets/swingToStrength.json");
        closeConnection();
    }

    /**
     * Connects to the databse
     */
    public static void connectDatabse(){
        url = "jdbc:mysql://localhost:3306/";
        dbName = "gymnastics";
        driver = "com.mysql.jdbc.Driver";
        userName = "root";
        password = "";
    }

    /**
     * Parses through the given json file and populates a database with the skills in the file
     * @param filename
     */
    public static void parseJsonToDatabase(String filename){
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(new FileReader(filename));
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray skillList = (JSONArray) jsonObject.get("skills");
                Iterator skillIterator = skillList.iterator();
                while (skillIterator.hasNext()) {
                    JSONObject skill = (JSONObject) skillIterator.next();
                    String skillName = (String) skill.get("skillName");
                    String elementGroup = (String) skill.get("elementGroup");
                    String letterScore = (String) skill.get("letterScore");
                    double score = (double) skill.get("score");
                    String path = (String) skill.get("img_path");
                    String sql = "INSERT into skills VALUES(?,?,?,?,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, skillName);
                    statement.setString(2, elementGroup);
                    statement.setString(3, letterScore);
                    statement.setDouble(4, score);
                    statement.setString(5, path);
                    statement.execute();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the current database connection
     */
    public static void closeConnection(){
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
