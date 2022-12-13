package org.example.controllers;

import org.example.dao.BookDAO;
import org.example.dao.PersonDAO;
import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mikhail Suslov
 */
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDao;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDao, PersonDAO personDAO) {
        this.bookDao = bookDao;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        // Get all books from database and pass them to http-page
        model.addAttribute("books", bookDao.getAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @ModelAttribute("keeper") Person person, @PathVariable("id") int id) {
        // Get book by ID from database and pass them to http-page
        model.addAttribute("book", bookDao.get(id));
        model.addAttribute("people", personDAO.getAll());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        // Create empty book and pass them to http-page
        return "books/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.get(id));
        return "books/edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book) {
        bookDao.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDao.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String setReader(@ModelAttribute("keeper") Person person, @PathVariable("id") int id) {
        Book bookForSet = bookDao.get(id);
        bookForSet.setKeeper(personDAO.get(person.getId()));
        bookDao.update(id, bookForSet);
        return "redirect:/books/" + id;
    }
}
