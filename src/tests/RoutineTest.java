package tests;

import model.Routine;
import model.SkillLibrary;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.junit.Assert.*;
/**
 * Created by arjunmenon on 4/6/16.
 */
public class RoutineTest {
    SkillLibrary library;
    public static double EPSILON = 0.01;

    /**
     * Setup method that creates and initializes the library for the rest of the test suite
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        library = new SkillLibrary();
        library.parseDatabase();
    }

    /**
     * A few positive test cases of adding a skill to the routine
     * @throws Exception
     */
    @Test
    public void testAddSkillPositive() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Back Lever"));
        assertEquals(1,newRoutine.getNumElements());
        newRoutine.addSkill(library.findSkillByName("Kip Support"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        assertEquals(3,newRoutine.getNumElements());
    }

    /**
     * A few negative test cases of adding a skill ot the routine
     * @throws Exception
     */
    @Test
    public void testAddSkillNegative() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Back Lever"));
        assertEquals(1,newRoutine.getNumElements());
        newRoutine.addSkill(library.findSkillByName("Back Lever"));
        assertEquals(1,newRoutine.getNumElements());
    }

    /**
     * Postive tests to check if a skill is contained in a routine
     * @throws Exception
     */
    @Test
    public void testContainsSkillPositive() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Back Lever"));
        newRoutine.addSkill(library.findSkillByName("Kip Support"));
        newRoutine.addSkill(library.findSkillByName("Yamawaki"));
        assertEquals(true,newRoutine.containsSkill(library.findSkillByName("Back Lever")));
        assertEquals(true,newRoutine.containsSkill(library.findSkillByName("Kip Support")));
        assertEquals(true,newRoutine.containsSkill(library.findSkillByName("Yamawaki")));
    }

    /**
     * Negative tests to check if a skill is contained in a routine
     * @throws Exception
     */
    @Test
    public void testContainsSkillNegative() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        assertEquals(false,newRoutine.containsSkill(library.findSkillByName("Back Lever")));
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        assertEquals(false,newRoutine.containsSkill(library.findSkillByName("Back Lever")));
    }

    /**
     * Positive test to check whether a routine contains all element groups
     * @throws Exception
     */
    @Test
    public void testCheckAllGroupsPositive() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Kip Support"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Front Giant"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Maltese"));
        newRoutine.addSkill(library.findSkillByName("Double Back"));
        assertEquals(true,newRoutine.checkAllGroups());
    }

    /**
     * Negative test to see whether a routine contains all element groups
     * @throws Exception
     */
    @Test
    public void testCheckAllGroupsNegative() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Maltese"));
        newRoutine.addSkill(library.findSkillByName("Double Back"));
        assertEquals(false,newRoutine.checkAllGroups());
    }

    /**
     * Positive test to check whether there are enough skills in the routine
     * @throws Exception
     */
    @Test
    public void testCheckNumSkillsPositive() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Kip Support"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Front Giant"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Maltese"));
        newRoutine.addSkill(library.findSkillByName("Maltese Press Inverted Cross"));
        newRoutine.addSkill(library.findSkillByName("Double Back"));
        assertEquals(true,newRoutine.checkNumSkills());
    }

    /**
     * Negative test to check whether there are enough skills in the routine
     * @throws Exception
     */
    @Test
    public void testCheckNumSkillsNegative() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Maltese"));
        newRoutine.addSkill(library.findSkillByName("Double Back"));
        assertEquals(false,newRoutine.checkNumSkills());
    }

    /**
     * Positive check to see if there is only one valid dismount
     * @throws Exception
     */
    @Test
    public void testCheckDismountPositive() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Kip Support"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Front Giant"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Maltese"));
        newRoutine.addSkill(library.findSkillByName("Maltese Press Inverted Cross"));
        newRoutine.addSkill(library.findSkillByName("Double Back"));
        assertEquals(true,newRoutine.checkDismount());
    }

    /**
     * Negative test to see if there is dismount and only one.
     * @throws Exception
     */
    @Test
    public void testCheckDismountNegative() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Kip Support"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Front Giant"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Maltese"));
        newRoutine.addSkill(library.findSkillByName("Maltese Press Inverted Cross"));
        assertEquals(false,newRoutine.checkDismount());
        newRoutine.addSkill(library.findSkillByName("Double Back"));
        newRoutine.addSkill(library.findSkillByName("Double Back Lay"));
        assertEquals(false,newRoutine.checkDismount());
    }

    /**
     * Positive test to check whether the dismount is last
     * @throws Exception
     */
    @Test
    public void testCheckDismountIsLastPositive() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Kip Support"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Front Giant"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Maltese"));
        newRoutine.addSkill(library.findSkillByName("Maltese Press Inverted Cross"));
        newRoutine.addSkill(library.findSkillByName("Double Back"));
        assertEquals(true,newRoutine.checkDismountIsLast());
    }

    /**
     * Negative test to check whether the dismount is last
     * @throws Exception
     */
    @Test
    public void testCheckDismountIsLastNegative() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Kip Support"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Double Back"));
        newRoutine.addSkill(library.findSkillByName("Front Giant"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Maltese"));
        newRoutine.addSkill(library.findSkillByName("Maltese Press Inverted Cross"));
        assertEquals(false,newRoutine.checkDismountIsLast());
    }

    /**
     * Positive check to make sure there are not too many strength elements in a sequence
     * @throws Exception
     */
    @Test
    public void testCheckNotTooManyStrengthPositive() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Kip Support"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Front Giant"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Maltese"));
        newRoutine.addSkill(library.findSkillByName("Maltese Press Inverted Cross"));
        newRoutine.addSkill(library.findSkillByName("Double Back"));
        newRoutine.validateRoutine();
        assertEquals(true,newRoutine.checkNotTooManyStrength());
    }

    /**
     * Negative test to make suere there are not too many strength elements in a sequence
     * @throws Exception
     */
    @Test
    public void testCheckNotTooManyStrengthNegative() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Back Lever"));
        newRoutine.addSkill(library.findSkillByName("Nakayama 2"));
        newRoutine.addSkill(library.findSkillByName("Cross Straight Press Inverted Cross"));
        newRoutine.addSkill(library.findSkillByName("Double Back Lay"));
        newRoutine.validateRoutine();
        assertEquals(false,newRoutine.checkNotTooManyStrength());
    }

    /**
     * Positive test for whether there is a proper swing between strength sequences
     * @throws Exception
     */
    @Test
    public void testCheckSwingAfterStrengthPositive() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Back Lever"));
        newRoutine.addSkill(library.findSkillByName("Nakayama 2"));
        newRoutine.addSkill(library.findSkillByName("Jonasson"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Maltese"));
        newRoutine.addSkill(library.findSkillByName("Double Back Lay"));
        newRoutine.validateRoutine();
        assertEquals(true,newRoutine.checkSwingAfterStrength());
    }

    /**
     * Negative test for a proper swing between strength sequences
     * @throws Exception
     */
    @Test
    public void testCheckSwingAfterStrengthNegative() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Back Lever"));
        newRoutine.addSkill(library.findSkillByName("Nakayama 2"));
        newRoutine.addSkill(library.findSkillByName("Inlocate"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Maltese"));
        newRoutine.addSkill(library.findSkillByName("Double Back Lay"));
        newRoutine.validateRoutine();
        assertEquals(false,newRoutine.checkSwingAfterStrength());
    }

    /**
     * First simple routine to be scored. Does not have any element group deductions or dismount deductions
     * @throws Exception
     */
    @Test
    public void testScoreRoutine1() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Front Lever"));
        newRoutine.addSkill(library.findSkillByName("Back Lever"));
        newRoutine.addSkill(library.findSkillByName("Nakayama 2"));
        newRoutine.addSkill(library.findSkillByName("Yamawaki"));
        newRoutine.addSkill(library.findSkillByName("Jonasson"));
        newRoutine.addSkill(library.findSkillByName("Whip it V-Cross"));
        newRoutine.addSkill(library.findSkillByName("Cross Straight Press Inverted Cross"));
        newRoutine.addSkill(library.findSkillByName("Inverted Cross Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Front Giant"));
        newRoutine.addSkill(library.findSkillByName("Back Off Lay 3"));
        newRoutine.validateRoutine();
        newRoutine.scoreRoutine();
        assertEquals(16.2,newRoutine.getStartValue(),EPSILON);
    }

    /**
     * Second simple routine to be scored. Contains all element groups but does not meet dismount requirement
     * @throws Exception
     */
    @Test
    public void testScoreRoutine2() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Azarian Cross"));
        newRoutine.addSkill(library.findSkillByName("Kip Support"));
        newRoutine.addSkill(library.findSkillByName("Maltese"));
        newRoutine.addSkill(library.findSkillByName("Nakayama 2"));
        newRoutine.addSkill(library.findSkillByName("Inlocate"));
        newRoutine.addSkill(library.findSkillByName("Jonasson"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Straddle Planche"));
        newRoutine.addSkill(library.findSkillByName("L-sit"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Front Giant"));
        newRoutine.addSkill(library.findSkillByName("Double Back"));
        newRoutine.validateRoutine();
        newRoutine.scoreRoutine();
        assertEquals(15.2,newRoutine.getStartValue(),EPSILON);
    }

    /**
     * Final simple routine to be tested. Is missing both group requirements and dismount requirements.
     * @throws Exception
     */
    @Test
    public void testScoreRoutine3() throws Exception {
        Routine newRoutine = new Routine("routine1","SR");
        newRoutine.addSkill(library.findSkillByName("Back Lever"));
        newRoutine.addSkill(library.findSkillByName("Inlocate"));
        newRoutine.addSkill(library.findSkillByName("Back Uprise Straddle L-sit"));
        newRoutine.addSkill(library.findSkillByName("Press Handstand"));
        newRoutine.addSkill(library.findSkillByName("Handstand Lower to Back Lever"));
        newRoutine.addSkill(library.findSkillByName("Kip Support"));
        newRoutine.addSkill(library.findSkillByName("Cross"));
        newRoutine.addSkill(library.findSkillByName("Front Off Pike or Lay"));
        newRoutine.validateRoutine();
        newRoutine.scoreRoutine();
        assertEquals(12.5,newRoutine.getStartValue(),EPSILON);
    }
}
