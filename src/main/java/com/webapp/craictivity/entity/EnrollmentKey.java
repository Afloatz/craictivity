package com.webapp.craictivity.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//It's the composite key for the table enrollment
@Embeddable
public class EnrollmentKey implements Serializable {

    @Column(name = "workshop_id")
    Long workshopId;

    @Column(name = "participant_id")
    Long participantId;

    public EnrollmentKey() {
    }

    public EnrollmentKey(Long workshopId, Long participantId) {
        this.workshopId = workshopId;
        this.participantId = participantId;
    }

    public Long getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(Long workshopId) {
        this.workshopId = workshopId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EnrollmentKey that = (EnrollmentKey) o;
//        return Objects.equals(workshopId, that.workshopId) && Objects.equals(participantId, that.participantId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(workshopId, participantId);
//    }
}
