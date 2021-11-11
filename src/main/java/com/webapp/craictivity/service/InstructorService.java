package com.webapp.craictivity.service;

import com.webapp.craictivity.entity.Instructor;

import java.util.List;

public interface InstructorService {

    List<Instructor> getAllInstructors();
    Instructor saveInstructor(Instructor instructor);
}
