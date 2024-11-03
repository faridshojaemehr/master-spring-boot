package com.master.spring_boot.book;


import com.master.spring_boot.utils.SortingOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public List<Book> getAllBooks( @RequestParam(value = "sort", required = false, defaultValue = "ASC") SortingOrder sort) {
        return bookService.getBooks(sort);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Integer id) {
        Optional<Book> selectedBook =bookService.getBookById(id);
        return ResponseEntity.ok().body(selectedBook);
    }



    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity updateBook(@PathVariable Integer id, @RequestBody BookUpdateRequest book) {
        bookService.updateBook(id,book);
        return ResponseEntity.ok().build();
    }
}
