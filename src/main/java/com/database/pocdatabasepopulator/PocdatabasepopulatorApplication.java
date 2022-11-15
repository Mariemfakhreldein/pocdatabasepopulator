package com.database.pocdatabasepopulator;

import com.database.pocdatabasepopulator.entities.PromotionDetails;
import com.database.pocdatabasepopulator.entities.Segment;
import com.database.pocdatabasepopulator.entities.SegmentSubscriber;
import com.database.pocdatabasepopulator.entities.Subscriber;
import com.database.pocdatabasepopulator.enums.PromotionStatus;
import com.database.pocdatabasepopulator.enums.Type;
import com.database.pocdatabasepopulator.repositories.PromotionDetailsRepository;
import com.database.pocdatabasepopulator.repositories.SegmentSubscriberRepository;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PocdatabasepopulatorApplication implements CommandLineRunner {

    @Autowired
    private SegmentSubscriberRepository segmentSubscriberRepository;
    @Autowired
    private PromotionDetailsRepository promotionDetailsRepository;

    DataFactory df = new DataFactory();

    private int subscribersCount = 10000000;
    public static void main(String[] args) {
        SpringApplication.run(PocdatabasepopulatorApplication.class, args);
    }

    @Override
//    @Transactional
    public void run(String... args) throws Exception {

        List<Segment> segments  = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        //PROMO1
        PromotionDetails promotionDetails1 = new PromotionDetails();
        promotionDetails1.setName("PROMO1");

        Segment segment11 = new Segment();
        segment11.setName("SEGMENT11");
        promotionDetails1.addSegment(segment11);
//        segments.add(segment11);

        Segment segment12 = new Segment();
        segment12.setName("SEGMENT12");
        promotionDetails1.addSegment(segment12);
//        segments.add(segment12);

        Segment segment13 = new Segment();
        segment13.setName("SEGMENT13");
        promotionDetails1.addSegment(segment13);
//        segments.add(segment13);

        promotionDetails1 = promotionDetailsRepository.save(promotionDetails1);
        segments.addAll(promotionDetails1.getSegments());

        //PROMO2
        PromotionDetails promotionDetails2 = new PromotionDetails();
        promotionDetails2.setName("PROMO2");

        Segment segment21 = new Segment();
        segment21.setName("SEGMENT21");
        promotionDetails2.addSegment(segment21);
//        segments.add(segment21);

        Segment segment22 = new Segment();
        segment22.setName("SEGMENT22");
        promotionDetails2.addSegment(segment22);
//        segments.add(segment22);

        promotionDetails2 = promotionDetailsRepository.save(promotionDetails2);
        segments.addAll(promotionDetails2.getSegments());

        //PROMO3
        PromotionDetails promotionDetails3 = new PromotionDetails();
        promotionDetails3.setName("PROMO3");

        Segment segment31 = new Segment();
        segment31.setName("SEGMENT31");
        promotionDetails3.addSegment(segment31);
//        segments.add(segment31);

        Segment segment32 = new Segment();
        segment32.setName("SEGMENT32");
        promotionDetails3.addSegment(segment32);
//        segments.add(segment32);

        Segment segment33 = new Segment();
        segment33.setName("SEGMENT33");
        promotionDetails3.addSegment(segment33);
//        segments.add(segment33);

        promotionDetails3 = promotionDetailsRepository.saveAndFlush(promotionDetails3);
        segments.addAll(promotionDetails3.getSegments());


        System.out.println("added promos and segments");
        String msisdn = "1200000000";
        List<SegmentSubscriber> segmentSubscribers = new ArrayList<>();
        //subscribers
        for(int i = 1; i <= subscribersCount ; ++i) {

            Subscriber subscriber = new Subscriber();
            subscriber.setName(df.getName());
            int promotionStatusSize = PromotionStatus.values().length;
            subscriber.setPromotionStatus(PromotionStatus.values()[getRandomNumber(0, promotionStatusSize)]);
            int typesSize = Type.values().length;
            subscriber.setType(Type.values()[getRandomNumber(0, typesSize)]);
            subscriber.setContractId(df.getNumberText(5));
            subscriber.setMsisdn("0" + msisdn);

            msisdn = Long.toString(Long.parseLong(msisdn) + 1);
//            System.out.println(msisdn);


            SegmentSubscriber segmentSubscriber = new SegmentSubscriber();
            segmentSubscriber.setSubscriber(subscriber);
            segmentSubscriber.setSegment(segments.get(getRandomNumber(0, segments.size())));

            segmentSubscribers.add(segmentSubscriber);

            if(i%1000000 == 0){
                segmentSubscriberRepository.saveAll(segmentSubscribers);
//                segmentSubscriberRepository.flush();
            segmentSubscribers.clear();
            }
        }

        long stopTime = System.currentTimeMillis();
        double elapsedTime = stopTime - startTime;
        System.out.println("****" + elapsedTime/1000.0);

//        while (true){}
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
