package com.webapp.craictivity.repository;

import com.webapp.craictivity.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

//2 parameters: the type of the JPA entity and of the primary key
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
