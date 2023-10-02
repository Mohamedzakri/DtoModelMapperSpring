package com.example.modelmapperdtospring.controllers;

import com.example.modelmapperdtospring.dto.BookDto;
import com.example.modelmapperdtospring.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getBookById/{id}")
    public BookDto getBookById(long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/getBooks")
    public List<BookDto> getBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/updateBookById/{id}")
    public BookDto updateBookById(@PathVariable long id, @RequestBody BookDto bookDto) {
        return bookService.updateBookById(bookDto, id);
    }
    @PutMapping("/updateBookById/{id}")
    public BookDto updateBookByIda(@PathVariable long id, @RequestBody BookDto bookDto) {
        return bookService.updateBookById(bookDto, id);
    }
    @PostMapping("/createBook")
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @DeleteMapping("/deleteBook/{id}")
    public BookDto deleteBookById(@PathVariable long id) {
        return bookService.deleteBookById(id);
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<BookDto> getBook(long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }
}
