package com.master.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public enum Gender {MALE, FEMALE};
	public record Person(int id,String name,int age, Gender gender){}
	public static List<Person> people = new ArrayList<>();
	static {
		people.add(new Person(1,"John",18,Gender.MALE));
		people.add(new Person(2,"Jane",20,Gender.FEMALE));
		people.add(new Person(3,"Bob",24,Gender.MALE));
		people.add(new Person(4,"Mary",28,Gender.FEMALE));
	}

	@GetMapping
	public List<Person> getPersons(){
		return people;
	}

}
