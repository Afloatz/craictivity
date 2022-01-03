package com.webapp.craictivity;

import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.repository.WorkshopRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkshopRepositoryTests {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveWorkshopTest(){
        Workshop workshop = new Workshop("Theatre", "11/01/2021", "7.00pm",
                2.5, 15, null);

        workshopRepository.save(workshop);

        Assertions.assertThat(workshop.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getWorkshopTest(){
        //Check the Id of the new workshop created in the database first (if there are existing ones)
        Workshop workshop = workshopRepository.findById(1L).get();

        Assertions.assertThat(workshop.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfWorkshopTest(){
        List<Workshop> workshops = workshopRepository.findAll();
        Assertions.assertThat(workshops.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateWorkshopTest(){
        Workshop workshop = workshopRepository.findById(1L).get();
        workshop.setTitle("Dance");
        Workshop workshopUpdated = workshopRepository.save(workshop);
        Assertions.assertThat(workshopUpdated.getTitle()).isEqualTo("Dance");
    }

}
