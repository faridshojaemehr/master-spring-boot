package com.master.spring_boot.book;

import com.master.spring_boot.utils.SortingOrder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
         this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(SortingOrder sort) {
        return bookRepository.getBooks().stream()
                .sorted(sort == SortingOrder.DESC ? Comparator.comparing(Book::id).reversed() : Comparator.comparing(Book::id))
                .collect(Collectors.toList());
    }

    public Optional<Book> getBookById(Integer id){
        return bookRepository.getBooks()
                .stream()
                .filter(book -> book.id().equals(id)).findFirst();
    }


    public void addBook(Book book){
        bookRepository.getBooks()
                .add(new Book(bookRepository.getIdGenerator().incrementAndGet(),book.title(),book.author()));
    }

    public void updateBook(Integer id ,BookUpdateRequest bookUpdateRequest){
        bookRepository.getBooks()
                .stream()
                .filter(b -> b.id().equals(id))
                .findFirst()
                .ifPresent(existingBook -> {
                    int index = bookRepository.getBooks().indexOf(existingBook);
                    Book updateBook = new Book(
                            existingBook.id(),
                            bookUpdateRequest.title() != null && !bookUpdateRequest.title().isEmpty() ? bookUpdateRequest.title() : existingBook.title(),
                            bookUpdateRequest.author() != null && !bookUpdateRequest.author().isEmpty() ? bookUpdateRequest.author() : existingBook.author()
                    );
                    bookRepository.getBooks().set(index, updateBook);
                });
    }


    public void deleteBook(Integer id){
        bookRepository.getBooks().removeIf(book -> book.id().equals(id));
    }


}
