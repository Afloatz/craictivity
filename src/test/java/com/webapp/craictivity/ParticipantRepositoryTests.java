package com.webapp.craictivity;

import com.webapp.craictivity.entity.Participant;
import com.webapp.craictivity.repository.ParticipantRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParticipantRepositoryTests {

    @Autowired
    private ParticipantRepository participantRepository;

    @Test
    public void saveParticipantTest(){
        Participant participant = new Participant("Jean", "Delop"
                , "jean@gmail.com", "08741454", null);

        participantRepository.save(participant);

        Assertions.assertThat(participant.getId()).isGreaterThan(0);
    }
}
