package com.webapp.craictivity.repository;

import com.webapp.craictivity.entity.Enrollment;
import com.webapp.craictivity.entity.EnrollmentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentKey> {
}
