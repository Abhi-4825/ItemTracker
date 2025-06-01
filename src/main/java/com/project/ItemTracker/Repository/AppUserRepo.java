
package com.project.ItemTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ItemTracker.Model.AppUser;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, String> {

}
