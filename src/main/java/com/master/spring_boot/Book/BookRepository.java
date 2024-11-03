package com.master.spring_boot.Book;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class BookRepository {

    private static final AtomicInteger idGenerator = new AtomicInteger();
    private static List<Book> books = new ArrayList<>();

    static {
        books.add(new Book(idGenerator.incrementAndGet(), "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book(idGenerator.incrementAndGet(), "1984", "George Orwell"));
        books.add(new Book(idGenerator.incrementAndGet(), "Pride and Prejudice", "Jane Austen"));
        books.add(new Book(idGenerator.incrementAndGet(), "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(idGenerator.incrementAndGet(), "Moby Dick", "Herman Melville"));
        books.add(new Book(idGenerator.incrementAndGet(), "War and Peace", "Leo Tolstoy"));
        books.add(new Book(idGenerator.incrementAndGet(), "The Catcher in the Rye", "J.D. Salinger"));
        books.add(new Book(idGenerator.incrementAndGet(), "The Hobbit", "J.R.R. Tolkien"));
        books.add(new Book(idGenerator.incrementAndGet(), "Crime and Punishment", "Fyodor Dostoevsky"));
        books.add(new Book(idGenerator.incrementAndGet(), "Brave New World", "Aldous Huxley"));

    }

    public AtomicInteger getIdGenerator() {
        return idGenerator;
    }

    public List<Book> getBooks() {
        return books;
    }
}
