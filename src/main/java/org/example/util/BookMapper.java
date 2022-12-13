package org.example.util;

import org.example.dao.PersonDAO;
import org.example.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mikhail Suslov
 */
@Component
public class BookMapper implements RowMapper<Book> {

    private final PersonDAO personDAO;

    @Autowired
    public BookMapper(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        book.setKeeper(personDAO.get(rs.getInt("person_id")));

        return book;
    }
}
