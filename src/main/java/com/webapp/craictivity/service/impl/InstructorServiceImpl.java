package com.webapp.craictivity.service.impl;

import com.webapp.craictivity.entity.Instructor;
import com.webapp.craictivity.repository.InstructorRepository;
import com.webapp.craictivity.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(final InstructorRepository instructorRepository) {
        super();
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll(); //returns the list of instructors
    }

    @Override
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }
}
