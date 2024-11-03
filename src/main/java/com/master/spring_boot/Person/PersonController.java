package com.master.spring_boot.Person;

import com.master.spring_boot.Utils.SortingOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    private final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping
    public List<Person> getPeople(
            @RequestParam(value = "sort", required = false, defaultValue = "ASC") SortingOrder sort
            ) {
        return personService.getPeople(sort);
    }


    @GetMapping("{id}")
    public ResponseEntity<Optional<Person>> getPersonById(@PathVariable("id") Integer id){
        Optional<Person> selectedPerson = personService.getPersonById(id);
        return ResponseEntity.ok().body(selectedPerson);
    }


    @PostMapping("/addPerson")
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @PutMapping("{id}")
    public void updatePerson(@PathVariable Integer id, @RequestBody PersonUpdateRequest personUpdate) {
       personService.updatePerson(id, personUpdate);
    }

    @DeleteMapping("{id}")
    public void findPersonById(@PathVariable("id") Integer id){
        personService.deletePersonById(id);
    }
}
