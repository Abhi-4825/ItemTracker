package com.project.ItemTracker.Service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ItemTracker.Model.Person;
import com.project.ItemTracker.Repository.Personrepository;

@Service
public class PersonService {
	@Autowired
	Personrepository repo;
public List<Person> getPersonReturned(){
	return repo.findByReturnedAtIsNotNull();
	
}
public List<Person> getPersonNotReturned(){
	return repo.findByReturnedAtIsNull();
	
}
public void addPerson(Person person) {
	repo.save(person);
	
}
public Optional<Person> updatePerson(Long id, Person updated) {
    return repo.findById(id).map(person -> {
        person.setName(updated.getName());
        person.setItemName(updated.getItemName());
        return repo.save(person);
    });
}
public void delete(Long id) {
	repo.deleteById(id);
}
public Optional<Person> returned(Long id){
	return repo.findById(id).map(person->{
		person.setReturnedAt(LocalDateTime.now());
		return repo.save(person);
	});
}







}
