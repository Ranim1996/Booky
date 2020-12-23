package Test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.Country;

public class CountryTest {

    @Test //check whether country info are correct
    void NewCountryTest()
    {
        Country c = new Country("SY", "Syria");

        assertEquals("SY", c.getCode());
        assertEquals("Syria", c.getName());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // country name null
    void getCountryNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Country name must not be null");

        Country c= new Country();
        c.setName(null);
    }

    @Test // country name string is empty
    void getCountryNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Country name must not be empty");

        Country c= new Country();
        c.setName(" ");
    }

    @Test // country code null
    void getCountryCodeWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Country code must not be null");

        Country c= new Country();
        c.setCode(null);
    }

    @Test // country code string is empty
    void getCountryCodeWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Country code must not be empty");

        Country c= new Country();
        c.setCode(" ");
    }



}

