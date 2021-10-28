package com.webapp.craictivity;

import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CraictivityApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CraictivityApplication.class, args);
	}

	@Autowired
	private WorkshopRepository workshopRepository;

	@Override
	public void run(String... args) throws Exception {

//		//Add records to the database to test if it works
//		Workshop workshop1 = new Workshop("Theater", "01/11/2021", "2h", 25);
//		workshopRepository.save(workshop1);
//
//		Workshop workshop2 = new Workshop("Dance", "10/11/2021", "2h", 20);
//		workshopRepository.save(workshop2);
//
//		Workshop workshop3 = new Workshop("Singing", "12/11/2021", "1h", 15);
//		workshopRepository.save(workshop3);


	}
}
