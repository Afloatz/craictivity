package com.webapp.craictivity;

import com.webapp.craictivity.entity.Instructor;
import com.webapp.craictivity.repository.InstructorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InstructorRepositoryTests {

    @Autowired
    private InstructorRepository instructorRepository;

    //JUnit test
    @Test
    public void saveInstructorTest(){
        Instructor instructor = Instructor.builder()
                .firstName("Charles")
                .lastName("Pouli")
                .email("charles@free.fr")
                .build();
        instructorRepository.save(instructor);

        //Check that an id was generated  (i.e instructor has been created)
        Assertions.assertThat(instructor.getId()).isGreaterThan(0);
    }
}
