package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.Language;

public class LanguageTest {

    @Test //check whether language info are correct
    public void NewCountryTest()
    {
        Language l = new Language("AR", "Arabic");

        assertEquals("AR", l.getCode());
        assertEquals("Arabic", l.getName());
    }

    @Rule // this rule is added to throw exceptions when its needed
    public ExpectedException thrown = ExpectedException.none();

    @Test // language name null
    public void getLanguageNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Language name must not be null");

        Language l= new Language();
        l.setName(null);
    }

    @Test // language name string is empty
    public void getLanguageNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Language name must not be empty");

        Language l= new Language();
        l.setName(" ");
    }

    @Test // language code null
    public void getLanguageCodeWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Langauge code must not be null");

        Language l= new Language();
        l.setCode(null);
    }

    @Test // language code string is empty
    public void getLanguageCodeWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Language code must not be empty");

        Language l= new Language();
        l.setCode(" ");
    }



}


