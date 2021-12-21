package com.webapp.craictivity.service.impl;

import com.webapp.craictivity.entity.Enrollment;
import com.webapp.craictivity.repository.EnrollmentRepository;
import com.webapp.craictivity.service.EnrollmentService;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(final EnrollmentRepository enrollmentRepository) {
        super();
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment load(Long id) {
        return enrollmentRepository.getById(id);
    }
}
