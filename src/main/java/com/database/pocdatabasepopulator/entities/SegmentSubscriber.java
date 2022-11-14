package com.database.pocdatabasepopulator.entities;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
public class SegmentSubscriber {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    @ManyToOne
    @JoinColumn(name = "segment_id")
    private Segment segment;

    public void setSubscriber(Subscriber subscriber){
        subscriber.addSegment(this);
        this.subscriber = subscriber;
    }
    public void setSegment(Segment segment){
        segment.addSubscriber(this);
        this.segment = segment;
    }

}
