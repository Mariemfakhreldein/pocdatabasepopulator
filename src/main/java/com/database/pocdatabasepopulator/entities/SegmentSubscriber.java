package com.database.pocdatabasepopulator.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SegmentSubscriber {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    @ManyToOne
    @JoinColumn(name = "segment_id")
    private Segment segment;

}
