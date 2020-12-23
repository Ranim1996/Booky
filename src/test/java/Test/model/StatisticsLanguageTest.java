package Test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.DTO.StatisticsLanguage;

public class StatisticsLanguageTest {

    @Test //check whether statistics per language info are correct
    void NewLanguageStaticTest()
    {
        StatisticsLanguage statisticsLanguage = new StatisticsLanguage("FR", 4);

        assertEquals("FR", statisticsLanguage.getName());
        assertEquals(4, statisticsLanguage.getY());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // static name is empty
    void getStaticNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Static name must not be empty");

        StatisticsLanguage statisticsLanguage = new StatisticsLanguage();
        statisticsLanguage.setName("");
    }

    @Test // static info is null
    void getStaticNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Static name must not be null");

        StatisticsLanguage statisticsLanguage = new StatisticsLanguage();
        statisticsLanguage.setName(null);
    }
}
