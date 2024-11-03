package com.master.spring_boot.Person;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class PersonRepository {

    public static final AtomicInteger idGenerator = new AtomicInteger();
    private static List<Person> people = new ArrayList<>();

    static {
        people.add(new Person(idGenerator.incrementAndGet(),"John",18,Gender.MALE));
        people.add(new Person(idGenerator.incrementAndGet(),"Jane",20,Gender.FEMALE));
        people.add(new Person(idGenerator.incrementAndGet(),"Bob",24,Gender.MALE));
        people.add(new Person(idGenerator.incrementAndGet(),"Mary",28,Gender.FEMALE));
    }

    public AtomicInteger getIdGenerator() {
        return idGenerator;
    }

    public List<Person> getPeople() {
        return people;
    }
}
