package Test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.BookType;
import service.model.DTO.StatisticsLanguage;
import service.model.DTO.StatisticsType;

public class StatisticsTypeTest {

    @Test //check whether statistics per type info are correct
    void NewTypeStaticTest()
    {
        StatisticsType statisticsType = new StatisticsType(BookType.Classics.name(), 4);

        assertEquals(BookType.Classics.name(), statisticsType.getLabel());
        assertEquals(4, statisticsType.getY());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // static name is empty
    void getStaticNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Static name must not be empty");

        StatisticsType statisticsType = new StatisticsType(BookType.Classics.name(), 4);
        statisticsType.setLabel("");
    }

    @Test // static info is null
    void getStaticNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Static name must not be null");

        StatisticsType statisticsType = new StatisticsType(BookType.Classics.name(), 4);
        statisticsType.setLabel(null);
    }
}
