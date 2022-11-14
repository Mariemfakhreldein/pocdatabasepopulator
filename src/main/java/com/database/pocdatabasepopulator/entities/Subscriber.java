package com.database.pocdatabasepopulator.entities;

import com.database.pocdatabasepopulator.enums.PromotionStatus;
import com.database.pocdatabasepopulator.enums.Type;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
public class Subscriber {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
    private Long id;

    @Column(unique=true)
    private String msisdn;
    private String name;
    @Enumerated(EnumType.STRING)
    private PromotionStatus promotionStatus;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String contractId;

    @OneToMany(mappedBy = "subscriber")
    private List<SegmentSubscriber> segments = new ArrayList<>();

    public void addSegment(SegmentSubscriber segmentSubscriber){
        segments.add(segmentSubscriber);
    }

    private void setSegments(List<SegmentSubscriber> segments){}
    private List<SegmentSubscriber> getSegments(){return null;}

}
