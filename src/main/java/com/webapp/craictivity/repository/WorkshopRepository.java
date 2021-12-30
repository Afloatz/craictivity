package com.webapp.craictivity.repository;

import com.webapp.craictivity.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//2 parameters: the type of the JPA entity and of the primary key
@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {

    //custom query for the search functionality
    @Query(value = "select * from Workshop w where w.title like %:keyword% ", nativeQuery = true)
    List<Workshop> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from Workshop w where w.id not in (select e.workshop_id from Enrollment e where e.participant_id = ?1)", nativeQuery = true)
    List<Workshop> findUnregistered(Long participant_id);
}
