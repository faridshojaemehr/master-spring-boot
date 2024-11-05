package com.master.spring_boot.person;


import com.master.spring_boot.exception.DuplicateResourseException;
import com.master.spring_boot.exception.ResourceNotFoundException;
import com.master.spring_boot.utils.SortingOrder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class PersonService {

    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getPeople(SortingOrder sort){
        return personRepository.findAll(Sort.by(Sort.Direction.valueOf(sort.name()),"id"));
    }


    public Person getPersonById(Integer id){
        return personRepository.findById(id)
                .orElseThrow(() ->
                     new ResourceNotFoundException("Person with id: " + id + " does not exists"));
    }


    public void addPerson(NewPersonRequest newPerson) {
        if (newPerson.email() != null && !newPerson.email().isEmpty()) {

            boolean exists = personRepository.existsByEmail(newPerson.email());
            if (exists){
                throw new DuplicateResourseException("Email Taken");
            }
        }
        Person person = new Person(
                newPerson.name(),
                newPerson.age(),
                newPerson.gender(),
                newPerson.email()
        );
        personRepository.save(person);
    }


    public void updatePerson(Integer id,PersonUpdateRequest personUpdate) {
       Person person = personRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Person with id: " + id + " does not exists"));



                if (personUpdate.name() != null && !personUpdate.name().isEmpty() && !personUpdate.name().equals(person.getName()) ) {
                            person.setName(personUpdate.name());
                            personRepository.save(person);
                }
                if (personUpdate.email() != null && !personUpdate.email().isEmpty() && !personUpdate.email().equals(person.getEmail()) ) {
                    person.setEmail(personUpdate.email());
                    personRepository.save(person);
                }
                if (personUpdate.age() != null && !personUpdate.age().equals(person.getAge()) ) {
                   person.setAge(personUpdate.age());
                   personRepository.save(person);
                }

    }

    public void deletePersonById(Integer id){
        boolean existsById = personRepository.existsById(id);
        if (!existsById){
            new ResourceNotFoundException("Person with id: " + id + " does not exists");
        }
        personRepository.deleteById(id);

    }
}
