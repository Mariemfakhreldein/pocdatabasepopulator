package com.database.pocdatabasepopulator.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Subscriber {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String msisdn;

    private String name;
    private String promotionStatus;
    private String contractId;

    @OneToMany(mappedBy = "subscriber")
    private List<SegmentSubscriber> segments = new ArrayList<>();

    public void addSegment(SegmentSubscriber segmentSubscriber){
        segments.add(segmentSubscriber);
    }

    private void setSegments(List<SegmentSubscriber> segments){}
    private List<SegmentSubscriber> getSegments(){return null;}

}
