import lab.dao.CountryDao;
import lab.dao.CountryNotFoundException;
import lab.model.Country;
import lab.model.SimpleCountry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:orm.xml")
class CountryDaoImplTest {

    private Country exampleCountry = new SimpleCountry(1L, "Australia", "AU");

    @Autowired
    private CountryDao countryDao;

    @Test
    void testSaveCountry() {

        countryDao.save(exampleCountry);

        List<Country> countries  = countryDao.getCountries();
        assertEquals(1, countries.size());
        assertEquals(exampleCountry, countries.get(0));
    }

    @Test
    void testGtAllCountries() {

        countryDao.save(new SimpleCountry(1L, "Canada", "CA"));

        List<Country> countryList = countryDao.getCountries();
        assertEquals(2, countryList.size());
    }

    @Test
    void testGetCountryByName() throws CountryNotFoundException {
        Country country = countryDao.getCountryByName("Australia");
        assertEquals(exampleCountry, country);
    }

}
