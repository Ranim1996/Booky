package Test.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import service.ControllerPersistance.DataCountryController;
import service.model.Country;
import service.repository.BookyDatabaseException;
import service.repository.JDBCCountryRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CountryDataTest {

    @InjectMocks
    DataCountryController countryController;

    @Mock
    JDBCCountryRepository countryRepository;

    @Test
    void showAllCountries() throws BookyDatabaseException, SQLException {

        List<Country> countries = new ArrayList<>();
        Country country = new Country("USA", "America");

        countries.add(country);

        when(countryRepository.getCountries()).thenReturn(countries);

        List<Country> countryList = countryController.showAllCountries();

        assertEquals(countries, countryList);
    }

    @Test
    void showCountryByCode() throws BookyDatabaseException, SQLException {

        Country country = new Country("USA", "America");

        when(countryRepository.getCountryByCode("USA")).thenReturn(country);

        Country country1 = countryController.showCountryByCode("USA");

        assertEquals(country,country1);
    }
}
