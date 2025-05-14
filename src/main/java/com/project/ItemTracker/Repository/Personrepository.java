package com.project.ItemTracker.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.ItemTracker.Model.*;
import java.time.LocalDateTime;

@Repository
public interface Personrepository extends JpaRepository<Person, Long>{
  List<Person> findByReturnedAtIsNull();
  List<Person> findByReturnedAtIsNotNull();
}
