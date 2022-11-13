package com.database.pocdatabasepopulator;

import com.database.pocdatabasepopulator.entities.PromotionDetails;
import com.database.pocdatabasepopulator.entities.Segment;
import com.database.pocdatabasepopulator.repositories.PromotionDetailsRepository;
import com.database.pocdatabasepopulator.repositories.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PocdatabasepopulatorApplication implements CommandLineRunner {

    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private PromotionDetailsRepository promotionDetailsRepository;

    public static void main(String[] args) {
        SpringApplication.run(PocdatabasepopulatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PromotionDetails promotionDetails = new PromotionDetails();
        promotionDetails.setName("PROMO1");


        Segment segment = new Segment();
        segment.setName("SEGMENT11");


        promotionDetails.addSegment(segment);


        promotionDetailsRepository.save(promotionDetails);

        while (true){}
    }
}
