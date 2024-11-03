package com.master.spring_boot.person;


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


    public Optional<Person> getPersonById(Integer id){
        return personRepository.getPeople().stream()
                .filter(person -> person.id().equals(id)).findFirst();
    }


    public void addPerson(NewPersonRequest newPerson) {
        personRepository.getPeople()
                .add(
                        new Person(
                                personRepository.idGenerator.incrementAndGet(),
                                newPerson.name(),
                                newPerson.age(),
                                newPerson.gender()
                        )
                );
    }

    public void updatePerson(Integer id,PersonUpdateRequest personUpdate) {
        personRepository.getPeople().stream()
                .filter(el -> el.id().equals(id))
                .findFirst()
                .ifPresent(existingPerson -> {
                    var index = personRepository.getPeople().indexOf(existingPerson);
                    if (personUpdate.name() != null && !personUpdate.name().isEmpty() && !personUpdate.name().equals(existingPerson.name()) ) {
                        Person person = new Person(existingPerson.id(),personUpdate.name(),existingPerson.age(),existingPerson.gender());
                        personRepository.getPeople().set(index, person);
                    }
                    if (personUpdate.age() != null && !personUpdate.age().equals(existingPerson.age()) ) {
                        Person person = new Person(existingPerson.id(),existingPerson.name(),personUpdate.age(),existingPerson.gender());
                        personRepository.getPeople().set(index, person);
                    }

//					another way
//					-----------------
//					Person updatePerson = new Person(
//							existingPerson.id(),
//							personUpdate.name() != null && !personUpdate.name.isEmpty() ? personUpdate.name() : existingPerson.name(),
//							personUpdate.age() != null ? personUpdate.age() : existingPerson.age(),
//							existingPerson.gender()
//					);
//					getPeople().set(index, updatePerson);
                });
    }

    public void deletePersonById(Integer id){
         personRepository.getPeople().removeIf(person -> person.id().equals(id));
    }
}
