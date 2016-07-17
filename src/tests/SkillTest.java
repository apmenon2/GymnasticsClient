package tests;

import model.Skill;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test class for the Skill object in the model library.
 * Created by arjunmenon on 4/5/16.
 */
public class SkillTest{
    Skill testSkill1;
    Skill testSkill2;
    Skill testSkill3;
    Skill testSkill4;

    /**
     * Setup method for all tests in this suite
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        testSkill1 = new Skill("testSkill1", "Strength Hold", "SR", "A", 0.1, "test/path/test1.png");
        testSkill2 = new Skill("testSkill2", "Swing to Strength", "SR", "C", 0.3, "test/path/test2.png");
        testSkill3 = new Skill("testSkill3", "Swing to Handstand", "SR", "D", 0.4, "test/path/test3.png");
        testSkill4 = new Skill("testSkill4", "Kip and Swing", "SR", "E", 0.5, "test/path/test4.png");
    }

    /**
     * Test getting the ID of a Skill from its GroupName
     * @throws Exception
     */
    @Test
    public void testGetIDFromGroup() throws Exception {
        assertEquals(4, testSkill1.getElementGroupID());
        assertEquals(3, testSkill2.getElementGroupID());
        assertEquals(2, testSkill3.getElementGroupID());
    }

    /**
     * Positive test for determining if Skill is a strength element
     * @throws Exception
     */
    @Test
    public void testIsStrengthElementPositive() throws Exception {
        assertEquals(true, testSkill1.isStrengthElement());
        assertEquals(true, testSkill2.isStrengthElement());
    }

    /**
     * Negative test for determining if Skill is a strength element
     * @throws Exception
     */
    @Test
    public void testIsStrengthElementNegative() throws Exception {
        assertEquals(false, testSkill3.isStrengthElement());
        assertEquals(false, testSkill4.isStrengthElement());
    }

    /**
     * Positive test for determining if Skill is a swing element
     * @throws Exception
     */
    @Test
    public void testIsSwingElementPositive() throws Exception {
        assertEquals(true, testSkill3.isSwingElement());
        assertEquals(true, testSkill4.isSwingElement());
    }

    /**
     * Negative test for determining if Skill is a swing element
     * @throws Exception
     */
    @Test
    public void testIsSwingElementNegative() throws Exception {
        assertEquals(false, testSkill1.isSwingElement());
        assertEquals(false, testSkill2.isSwingElement());
    }


}
