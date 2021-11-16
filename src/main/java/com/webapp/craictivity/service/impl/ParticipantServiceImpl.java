package com.webapp.craictivity.service.impl;

import com.webapp.craictivity.entity.Participant;
import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.repository.ParticipantRepository;
import com.webapp.craictivity.service.ParticipantService;
import org.springframework.stereotype.Service;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private ParticipantRepository participantRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        super();
        this.participantRepository = participantRepository;
    }


    @Override
    public Participant saveParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

//    @Override
//    public Participant getParticipantById(Long id) {
//        return participantRepository.findById(id).get();
//    }
}
