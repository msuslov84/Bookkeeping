package org.example.dao;

import org.example.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mikhail Suslov
 */
@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Book> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Book.class);

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT book_id as id, title, author, year FROM book", ROW_MAPPER);
    }

    public Book get(int id) {
        return jdbcTemplate.query("SELECT book_id as id, title, author, year FROM book WHERE book_id=?", ROW_MAPPER, id)
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title,author,year) values(?,?,?) ", book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, year=? WHERE book_id=?", book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }
}
