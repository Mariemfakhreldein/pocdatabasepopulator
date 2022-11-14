package com.database.pocdatabasepopulator.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class PromotionDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "promotion",  cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Segment> segments = new ArrayList<>();

    public void addSegment(Segment segment){
        segment.setPromotion(this);
        segments.add(segment);
    }
}
