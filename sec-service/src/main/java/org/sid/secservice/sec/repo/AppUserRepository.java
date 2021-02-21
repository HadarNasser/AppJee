package org.sid.secservice.sec.repo;


import org.sid.secservice.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface AppUserRepository extends JpaRepository<AppUser,Long> {

          AppUser findByUsername(String username);
}
