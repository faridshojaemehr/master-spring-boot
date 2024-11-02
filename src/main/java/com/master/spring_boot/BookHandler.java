package com.master.spring_boot;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BookHandler {
    record Book(String name,String author){}
    public static List<Book> books = new ArrayList<>();
    static {
        books.add(new Book("Harry Potter", "J.K Rowling"));
        books.add(new Book("Atomic Hobbit", "Farid"));
        books.add(new Book("Harry Potter 2", "J.K Rowling 2"));
    }
    public ServerResponse getAllBooks(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
                List.of(books)
        );
    }

    public ServerResponse getBookByName(ServerRequest serverRequest) {
        String name = serverRequest.pathVariable("name");
        return ServerResponse.ok().body(
                books.stream()
                        .filter(book -> book.name.equals(name))
                        .findFirst()
        );
    }
}
