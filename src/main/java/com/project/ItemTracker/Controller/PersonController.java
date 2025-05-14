package com.project.ItemTracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ItemTracker.Model.Person;
import com.project.ItemTracker.Service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {
      @Autowired
	PersonService service;
      @GetMapping("/persons/notReturned")
	public List<Person> getPerson(){
		return service.getPersonNotReturned();
	}
      @GetMapping("/persons/Returned")
      public List<Person> getReturnedPerson(){
    	  return service.getPersonReturned();
      }
      @PostMapping("/addPerson")
      public void addPerson(@RequestBody Person person) {
    	  service.addPerson(person);
      }
      
      @PutMapping("/update/{id}")
      public ResponseEntity<Person> updateEntity(
          @PathVariable Long id,
          @RequestBody Person updated
      ) {
          return service.updatePerson(id, updated)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
      }
      @DeleteMapping("/delete/{id}")
      public ResponseEntity<Void> deletePerson(@PathVariable Long id){
    	  service.delete(id);
    	  return ResponseEntity.noContent().build();
      }
      @PatchMapping("/persons/{id}/return")
      public ResponseEntity<Person> returnItem(@PathVariable Long id) {
          return service.returned(id)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
      }
      
      
      
      
      
}
