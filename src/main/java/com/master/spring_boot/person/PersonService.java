package com.master.spring_boot.person;


import com.master.spring_boot.exception.DuplicateResourseException;
import com.master.spring_boot.exception.ResourceNotFoundException;
import com.master.spring_boot.utils.SortingOrder;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class PersonService {

    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople(SortingOrder sort){
      return personRepository.getPeople().stream()
                .sorted(sort == SortingOrder.DESC ? Comparator.comparing(Person::id).reversed() : Comparator.comparing(Person::id))
                .collect(Collectors.toList());
    }


    public Person getPersonById(Integer id){
        return personRepository.getPeople().stream()
                .filter(person -> person.id().equals(id))
                .findFirst()
                .orElseThrow(() ->
                     new ResourceNotFoundException("Person with id: " + id + " does not exists"));
    }


    public void addPerson(NewPersonRequest newPerson) {
        if (newPerson.email() != null && !newPerson.email().isEmpty()) {
            boolean exists = personRepository.getPeople()
                    .stream()
                    .anyMatch(p -> p.email().equalsIgnoreCase(newPerson.email()));
                    if (exists){
                        throw new DuplicateResourseException("Email Taken");
                    }
        }
        personRepository.getPeople()
                .add(
                        new Person(
                                personRepository.idGenerator.incrementAndGet(),
                                newPerson.name(),
                                newPerson.age(),
                                newPerson.gender(),
                                newPerson.email()
                        )
                );
    }

    public void updatePerson(Integer id,PersonUpdateRequest personUpdate) {
        Person p = personRepository.getPeople().stream()
                .filter(person -> person.id().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Person with id: " + id + " does not exists"));

                var index = personRepository.getPeople().indexOf(p);
                if (personUpdate.name() != null && !personUpdate.name().isEmpty() && !personUpdate.name().equals(p.name()) ) {
                    Person person = new Person(p.id(),personUpdate.name(),p.age(),p.gender(),p.email());
                    personRepository.getPeople().set(index, person);
                }
                if (personUpdate.email() != null && !personUpdate.email().isEmpty() && !personUpdate.email().equals(p.email()) ) {
                    Person person = new Person(p.id(),personUpdate.name(),p.age(),p.gender(),p.email());
                    personRepository.getPeople().set(index, person);
                }
                if (personUpdate.age() != null && !personUpdate.age().equals(p.age()) ) {
                    Person person = new Person(p.id(),p.name(),personUpdate.age(),p.gender(),p.email());
                    personRepository.getPeople().set(index, person);
                }

    }

    public void deletePersonById(Integer id){
        Person person = personRepository.getPeople()
                .stream()
                .filter(user -> user.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exists"));
        personRepository.getPeople().remove(person);

    }
}
