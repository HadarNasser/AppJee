package org.sid.secservice.sec.repo;

import org.sid.secservice.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface AppRoleRepository extends JpaRepository<AppRole,Long> {

     AppRole findByRoleName(String roleName);
}
