package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mikhail Suslov
 */
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Person> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Person.class);

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT person_id as id, name, year FROM person", ROW_MAPPER);
    }

    public Person get(int id) {
        return jdbcTemplate.query("SELECT person_id as id, name, year FROM person WHERE person_id=?", ROW_MAPPER, id)
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name,year) values(?,?) ", person.getName(), person.getYear());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name=?, year=? WHERE person_id=?", person.getName(), person.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }
}
