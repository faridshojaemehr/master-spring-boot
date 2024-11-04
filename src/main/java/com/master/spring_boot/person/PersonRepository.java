package com.master.spring_boot.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    boolean existsByEmail(String email);
    Person findById(int id);
}
