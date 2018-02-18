package lab.dao.jpa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Repository;

import lab.dao.CountryDao;
import lab.model.Country;

@Repository("countryDao")
public class CountryJpaDaoImpl extends JpaDao implements CountryDao {

	@Override
	public Country save(Country country) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		em.merge(country);

		transaction.commit();

		if (em != null)
			em.close();

		return country;
	}

	@Override
	public List<Country> getCountries() {

		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		List<Country> countries = em.createQuery(
				"select c from SimpleCountry c", Country.class)
				.getResultList();

		transaction.commit();

		if (em != null)
			em.close();

		return countries;
	}// getAllcountries()

	@Override
	public List<Country> getCountriesStartWith(String name) {
		return null;
	}

	@Override
	public void updateCountryName(String codeName, String newCountryName) {

	}

	@Override
	public void loadCountries() {

	}

	@Override
	public Country getCountryByCodeName(String codeName) {
		return null;
	}

	@Override
	public Country getCountryByName(String name) {
//		TODO: Implement it

		return null;
	}

}
