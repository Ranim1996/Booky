package Test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.Language;

public class LanguageTest {

    @Test //check whether language info are correct
    void NewLanguageTest()
    {
        Language l = new Language("AR", "Arabic");

        assertEquals("AR", l.getCode());
        assertEquals("Arabic", l.getName());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // language name null
    void getLanguageNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Language name must not be null");

        Language l= new Language();
        l.setName(null);
    }

    @Test // language name string is empty
    void getLanguageNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Language name must not be empty");

        Language l= new Language();
        l.setName(" ");
    }

    @Test // language code null
    void getLanguageCodeWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Langauge code must not be null");

        Language l= new Language();
        l.setCode(null);
    }

    @Test // language code string is empty
    void getLanguageCodeWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Language code must not be empty");

        Language l= new Language();
        l.setCode(" ");
    }

}


