package tests;

import model.Skill;
import model.SkillLibrary;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by arjunmenon on 4/6/16.
 */
public class SkillLibraryTest {
    SkillLibrary library;

    /**
     * Setup method used to set up library for all cases in the suite
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        library = new SkillLibrary();
        library.parseDatabase();
    }

    /**
     * Tests whether the json parses properly parses and populates the skill library
     * @throws Exception
     */
    @Test
    public void testParseJson() throws Exception {
        assertEquals(157,library.getSkillCount());
    }

    /**
     * Tests whether all skills are printed correctly and put into the search results list
     * @throws Exception
     */
    @Test
    public void testPrintAllSkills() throws Exception {
        library.printAllSkills();
        assertEquals(157,library.getSearchResults().size());
    }

    /**
     * Tests whether skills are printed correctly by their element group and if
     * the results list is properly populated.
     * @throws Exception
     */
    @Test
    public void testPrintByElementGroup() throws Exception {
        library.printByElementGroup("Kip and Swing");
        assertEquals(35,library.getSearchResults().size());
        library.printByElementGroup("Swing to Handstand");
        assertEquals(6,library.getSearchResults().size());
        library.printByElementGroup("Swing to Strength");
        assertEquals(30,library.getSearchResults().size());
        library.printByElementGroup("Strength Hold");
        assertEquals(62,library.getSearchResults().size());
        library.printByElementGroup("Dismount");
        assertEquals(24,library.getSearchResults().size());
    }

    /**
     * Tests a few positive search cases
     * @throws Exception
     */
    @Test
    public void testSearchLibraryPositive() throws Exception {
        library.searchLibrary("Back Uprise");
        assertEquals(49, library.getSearchResults().size());
        library.searchLibrary("Nakayama");
        assertEquals(2, library.getSearchResults().size());
    }

    /**
     * Tests a negative search case
     * @throws Exception
     */
    @Test
    public void testSearchLibraryNegative() throws Exception {
        library.searchLibrary("blah");
        assertEquals(0, library.getSearchResults().size());
    }

    /**
     * Tests to see if a skill is found properly by its exact name
     * @throws Exception
     */
    @Test
    public void testFindSkillByNamePositive() throws Exception {
        Skill testSkill = library.findSkillByName("Nakayama 1");
        assertNotNull(testSkill);
        testSkill = library.findSkillByName("Azarian Cross");
        assertNotNull(testSkill);
        testSkill = library.findSkillByName("Front Giant");
        assertNotNull(testSkill);
    }
}
