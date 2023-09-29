package com.example.modelmapperdtospring.services;

import org.modelmapper.*;
import com.example.modelmapperdtospring.dto.BookDto;
import com.example.modelmapperdtospring.models.Book;
import com.example.modelmapperdtospring.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepo bookRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public BookService( BookRepo bookRepo, ModelMapper modelMapper ){
        this.bookRepo = bookRepo;
        this.modelMapper = modelMapper;
    }

    public List<BookDto> getAllBooks(){
        List<Book> books = bookRepo.findAll();
        return books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    public BookDto getBookById(long id){
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isEmpty()){
            System.out.println("no book by the id: "+id);
            return null;
        }
        else {
            return modelMapper.map(optionalBook.get(), BookDto.class);
        }
    }

    public BookDto createBook(BookDto bookDto){
        Book book = modelMapper.map(bookDto, Book.class);
        Book savedBook = bookRepo.save(book);
        return modelMapper.map(savedBook, BookDto.class);
    }

    public BookDto updateBookById(BookDto bookDto, long id){
        if (!bookRepo.existsById(id)){
            throw new RuntimeException("book with the id doesn't exist");
        }
        else {
            bookDto.setId(id);
            Book book = modelMapper.map(bookDto, Book.class);
            Book bookUpdated = bookRepo.save(book);
            return modelMapper.map(bookUpdated, BookDto.class);
        }
    }
    public BookDto deleteBookById(long id){
        if (bookRepo.findById(id).isEmpty()){
            throw new RuntimeException("no such book found to be deleted by this id: "+id );
        }
        Optional<Book> deletedBook = bookRepo.findById(id);
        bookRepo.deleteById(id);
        return modelMapper.map(deletedBook,BookDto.class);
    }
}
