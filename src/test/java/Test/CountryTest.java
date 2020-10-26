package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.Country;

public class CountryTest {

    @Test //check whether country info are correct
    public void NewCountryTest()
    {
        Country c = new Country("SY", "Syria");

        assertEquals("SY", c.getCode());
        assertEquals("Syria", c.getName());
    }

    @Rule // this rule is added to throw exceptions when its needed
    public ExpectedException thrown = ExpectedException.none();

    @Test // country name null
    public void getCountryNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Country name must not be null");

        Country c= new Country();
        c.setName(null);
    }

    @Test // country name string is empty
    public void getCountryNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Country name must not be empty");

        Country c= new Country();
        c.setName(" ");
    }

    @Test // country code null
    public void getCountryCodeWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Country code must not be null");

        Country c= new Country();
        c.setCode(null);
    }

    @Test // country code string is empty
    public void getCountryCodeWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Country code must not be empty");

        Country c= new Country();
        c.setCode(" ");
    }



}

