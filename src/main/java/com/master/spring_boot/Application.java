package com.master.spring_boot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/person")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner(ObjectMapper objectMapper) {
		String personString = "{\"id\":1\"name\":\"farid\"}";
        Person person = null;
        try {
            person = objectMapper.readValue(personString, Person.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(person);
        try {
            System.out.println(objectMapper.writeValueAsString(personString));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return args -> {

		};
	};

	public enum SortingOrder{DESC,ASC}
	public enum Gender {MALE, FEMALE};
	public static AtomicInteger idGenerator = new AtomicInteger();
	public record PersonUpdate(String name,Integer age){}
//	public record Person(Integer id,String name, Integer age, Gender gender){}
	public static List<Person> people = new ArrayList<>();
	static {
		people.add(new Person(idGenerator.incrementAndGet(),"John",18,Gender.MALE));
		people.add(new Person(idGenerator.incrementAndGet(),"Jane",20,Gender.FEMALE));
		people.add(new Person(idGenerator.incrementAndGet(),"Bob",24,Gender.MALE));
		people.add(new Person(idGenerator.incrementAndGet(),"Mary",28,Gender.FEMALE));
	}

	@GetMapping
	public ResponseEntity<List<Person>> getPersons(
			HttpMethod method,
			ServletResponse response,
			ServletRequest request,
			@RequestHeader("Content-Type") String contentType,
			@RequestParam(value = "sort", required = false, defaultValue = "ASC") SortingOrder sort,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
//		System.out.println(method);
//		System.out.println("response => " + response.isCommitted());
//		System.out.println("request => " + request.getLocalAddr());
//		System.out.println(contentType);
		List<Person> people1 = people.stream()
				.sorted(sort == SortingOrder.DESC ? Comparator.comparing(Person::getId).reversed() : Comparator.comparing(Person::getId))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(people1);
	}


	@GetMapping("{id}")
	public ResponseEntity<Optional<Person>> getPersonById(
			@PathVariable("id") int id
			){
		Optional<Person> selectedPerson = people.stream()
				.filter(person -> person.getId().equals(id)).findFirst();
		return ResponseEntity.ok().body(selectedPerson);
	}


	@PostMapping("/add")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		people.add(new Person(idGenerator.incrementAndGet(),person.getName(),person.getAge(),person.getGender()));
		return ResponseEntity.ok().body(person);
	}

	@PutMapping("{id}")
	public void updatePerson(@PathVariable int id, @RequestBody PersonUpdate personUpdate) {
		people.stream()
				.filter(el -> el.getId().equals(id))
				.findFirst()
				.ifPresent(existingPerson -> {
					var index = people.indexOf(existingPerson);
					if (personUpdate.name != null && !personUpdate.name.isEmpty() && !personUpdate.name.equals(existingPerson.getName()) ) {
						Person person = new Person(existingPerson.getId(),personUpdate.name,existingPerson.getAge(),existingPerson.getGender());
						people.set(index, person);
					}
					if (personUpdate.age != null && !personUpdate.age.equals(existingPerson.getAge()) ) {
						Person person = new Person(existingPerson.getId(),existingPerson.getName(),personUpdate.age(),existingPerson.getGender());
						people.set(index, person);
					}

//					second way
//					-----------------
//					Person updatePerson = new Person(
//							existingPerson.id(),
//							personUpdate.name() != null && !personUpdate.name.isEmpty() ? personUpdate.name() : existingPerson.name(),
//							personUpdate.age() != null ? personUpdate.age() : existingPerson.age(),
//							existingPerson.gender()
//					);
//					people.set(index, updatePerson);
				});
	}

	@DeleteMapping("{id}")
	public ResponseEntity<List<Person>> findPersonById(
			@PathVariable("id") int id
	){
		people.removeIf(person -> person.getId().equals(id));
		return ResponseEntity.ok().body(people);
	}

}
