package com.project.ItemTracker.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
@Entity
public class Person {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String name;
     private String itemName;
     private LocalDateTime takenAt;
     private LocalDateTime returnedAt;
	public Person(Long id, String name, String itemName, LocalDateTime takenAt, LocalDateTime returnedAt) {
		super();
		this.id = id;
		this.name = name;
		this.itemName = itemName;
		this.takenAt = takenAt;
		this.returnedAt = returnedAt;
	}
	public Person() {
		super();
	}
	@PrePersist
	public void onCreate() {
		this.takenAt=LocalDateTime.now();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public LocalDateTime getTakenAt() {
		return takenAt;
	}
	public void setTakenAt(LocalDateTime takenAt) {
		this.takenAt = takenAt;
	}
	public LocalDateTime getReturnedAt() {
		return returnedAt;
	}
	public void setReturnedAt(LocalDateTime returnedAt) {
		this.returnedAt = returnedAt;
	}
     
}
