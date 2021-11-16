package com.webapp.craictivity.repository;

import com.webapp.craictivity.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
