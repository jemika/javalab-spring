package lab.dao;

import java.util.List;

import lab.model.Country;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CountryDao extends JdbcDaoSupport {
	private static final String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values ('%s', '%s')";

	private static final String GET_ALL_COUNTRIES_SQL = "select id, name, code_name from country";
	private static final String GET_COUNTRIES_BY_NAME_SQL = "select id, name, code_name from country where name like :name";
	private static final String GET_COUNTRY_BY_NAME_SQL = "select id, name, code_name from country where name = '%s' ";
	private static final String GET_COUNTRY_BY_CODE_NAME_SQL = "select id, name, code_name from country where code_name = '%s'";
	private static final String UPDATE_COUNTRY_NAME_BY_CODE_SQL = "update country SET name='%s' where code_name='%s'";

	public static final String[][] COUNTRY_INIT_DATA = {{"Australia", "AU"},
			{"Canada", "CA"}, {"France", "FR"}, {"Hong Kong", "HK"},
			{"Iceland", "IC"}, {"Japan", "JP"}, {"Nepal", "NP"},
			{"Russian Federation", "RU"}, {"Sweden", "SE"},
			{"Switzerland", "CH"}, {"United Kingdom", "GB"},
			{"United States", "US"}};

	private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();

	public List<Country> getCountryList() {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		String sql = GET_ALL_COUNTRIES_SQL;
		return jdbcTemplate.query(sql, COUNTRY_ROW_MAPPER);
	}

	public List<Country> getCountryListStartWith(String name) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				getDataSource());
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
				"name", name + "%");
		return namedParameterJdbcTemplate.query(GET_COUNTRIES_BY_NAME_SQL,
				sqlParameterSource, COUNTRY_ROW_MAPPER);
	}

	public void updateCountryName(String codeName, String newCountryName) {
		getJdbcTemplate().execute(String.format(UPDATE_COUNTRY_NAME_BY_CODE_SQL, newCountryName, codeName));
	}

	public void loadCountries() {
		for (String[] countryData : COUNTRY_INIT_DATA) {
			getJdbcTemplate().execute(
					String.format(LOAD_COUNTRIES_SQL, countryData[0], countryData[1]));
			}
		}

	public Country getCountryByCodeName(String codeName) {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();

		String sql = String.format(GET_COUNTRY_BY_CODE_NAME_SQL, codeName);

		return jdbcTemplate.query(sql, COUNTRY_ROW_MAPPER).get(0);
	}

	public Country getCountryByName(String name)  throws CountryNotFoundException {

		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		List<Country> countryList = jdbcTemplate.query(
				String.format(GET_COUNTRY_BY_NAME_SQL, name), COUNTRY_ROW_MAPPER);
		if (countryList.isEmpty()) {
			throw new CountryNotFoundException();
			}
		return countryList.get(0);
		}
}
