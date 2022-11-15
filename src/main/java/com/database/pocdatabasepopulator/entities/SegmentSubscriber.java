package com.database.pocdatabasepopulator.entities;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
public class SegmentSubscriber {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq", allocationSize = 100000)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    @ManyToOne
    @JoinColumn(name = "segment_id")
    private Segment segment;

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

}
