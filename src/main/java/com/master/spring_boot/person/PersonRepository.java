package com.master.spring_boot.person;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class PersonRepository {

    public static final AtomicInteger idGenerator = new AtomicInteger();
    private static List<Person> people = new ArrayList<>();

    static {
        people.add(new Person(idGenerator.incrementAndGet(), "John", 18, Gender.MALE, "john@example.com"));
        people.add(new Person(idGenerator.incrementAndGet(), "Jane", 20, Gender.FEMALE, "jane@example.com"));
        people.add(new Person(idGenerator.incrementAndGet(), "Bob", 24, Gender.MALE, "bob@example.com"));
        people.add(new Person(idGenerator.incrementAndGet(), "Mary", 28, Gender.FEMALE, "mary@example.com"));
        people.add(new Person(idGenerator.incrementAndGet(), "Rose", 18, Gender.MALE, "Rose@example.com"));
        people.add(new Person(idGenerator.incrementAndGet(), "Ahmad", 20, Gender.FEMALE, "Ahmad@example.com"));
        people.add(new Person(idGenerator.incrementAndGet(), "Ali", 24, Gender.MALE, "Ali@example.com"));
        people.add(new Person(idGenerator.incrementAndGet(), "Saba", 28, Gender.FEMALE, "Saba@example.com"));

    }

    public AtomicInteger getIdGenerator() {
        return idGenerator;
    }

    public List<Person>  getPeople() {
        return people;
    }
}
