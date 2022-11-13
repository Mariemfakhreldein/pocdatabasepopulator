package com.database.pocdatabasepopulator.entities;

import lombok.Data;

import javax.persistence.*;
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
    private List<SegmentSubscriber> subscribers;

}
