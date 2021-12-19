package com.webapp.craictivity.service;

import com.webapp.craictivity.entity.Enrollment;

public interface EnrollmentService {

    Enrollment saveEnrollment(Enrollment enrollment);
    Enrollment load(Long id);
}
