package com.database.pocdatabasepopulator.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Segment {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="promotion_id")
    private PromotionDetails promotion;


    @OneToMany(mappedBy = "segment")
    private List<SegmentSubscriber> subscribers = new ArrayList<>();

    public void addSubscriber(SegmentSubscriber subscriber){
        subscribers.add(subscriber);
    }

    private void setSubscribers(List<SegmentSubscriber> segmentSubscribers){
    }
    private List<SegmentSubscriber> getSubscribers(){
        return null;
    }

}
