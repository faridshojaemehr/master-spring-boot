package com.master.spring_boot.person;

import com.master.spring_boot.utils.SortingOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("api/v1/persons")
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
    public void addPerson(@RequestBody @Valid NewPersonRequest newPerson) {
        personService.addPerson(newPerson);
    }

    @PutMapping("{id}")
    public void updatePerson(@Valid @PathVariable Integer id, @RequestBody PersonUpdateRequest personUpdate) {
       personService.updatePerson(id, personUpdate);
    }

    @DeleteMapping("{id}")
    public void deletePersonById(
            @Valid
            @Positive
            @PathVariable("id") Integer id){
        personService.deletePersonById(id);
    }
}
