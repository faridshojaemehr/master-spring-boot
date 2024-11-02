package com.master.spring_boot;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


	public enum SortingOrder{DESC,ASC}
	public enum Gender {MALE, FEMALE};
	public static AtomicInteger idGenerator = new AtomicInteger();
	public record PersonUpdate(String name,Integer age){}
	public record Person(Integer id,String name,Integer age, Gender gender){}
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

		System.out.println(method);
		System.out.println("response => " + response.isCommitted());
		System.out.println("request => " + request.getLocalAddr());
		System.out.println(contentType);
		List<Person> people1 = people.stream()
				.sorted(sort == SortingOrder.DESC ? Comparator.comparing(Person::id).reversed() : Comparator.comparing(Person::id))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(people1);
	}


	@GetMapping("{id}")
	public ResponseEntity<Optional<Person>> getPersonById(
			@PathVariable("id") int id
			){
		Optional<Person> selectedPerson = people.stream()
				.filter(person -> person.id.equals(id)).findFirst();
		return ResponseEntity.ok().body(selectedPerson);
	}


	@PostMapping("/add")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		people.add(new Person(idGenerator.incrementAndGet(),person.name,person.age(),person.gender));
		return ResponseEntity.ok().body(person);
	}

	@PutMapping("{id}")
	public void updatePerson(@PathVariable int id, @RequestBody PersonUpdate personUpdate) {
		people.stream()
				.filter(el -> el.id.equals(id))
				.findFirst()
				.ifPresent(existingPerson -> {
					var index = people.indexOf(existingPerson);
					if (personUpdate.name != null && !personUpdate.name.isEmpty() && !personUpdate.name.equals(existingPerson.name) ) {
						Person person = new Person(existingPerson.id,personUpdate.name,existingPerson.age(),existingPerson.gender);
						people.set(index, person);
					}
					if (personUpdate.age != null && !personUpdate.age.equals(existingPerson.age) ) {
						Person person = new Person(existingPerson.id,existingPerson.name,personUpdate.age(),existingPerson.gender);
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
		people.removeIf(person -> person.id.equals(id));
		return ResponseEntity.ok().body(people);
	}

}
