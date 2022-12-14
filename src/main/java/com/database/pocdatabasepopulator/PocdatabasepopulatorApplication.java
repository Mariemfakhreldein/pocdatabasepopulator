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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PocdatabasepopulatorApplication implements CommandLineRunner {

    @Autowired
    private SegmentSubscriberRepository segmentSubscriberRepository;
    @Autowired
    private PromotionDetailsRepository promotionDetailsRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private EntityManager entityManager;

    DataFactory df = new DataFactory();

    private int subscribersCount = 1_000_000;
    public static void main(String[] args) {
        SpringApplication.run(PocdatabasepopulatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        long startTime = System.currentTimeMillis();

        transactionTemplate.execute(s -> {
            List<Segment> segments  = new ArrayList<>();

            //PROMO1
            PromotionDetails promotionDetails1 = new PromotionDetails();
            promotionDetails1.setName("PROMO1");

            Segment segment11 = new Segment();
            segment11.setName("SEGMENT11");
            promotionDetails1.addSegment(segment11);
            segments.add(segment11);

            Segment segment12 = new Segment();
            segment12.setName("SEGMENT12");
            promotionDetails1.addSegment(segment12);
            segments.add(segment12);

            Segment segment13 = new Segment();
            segment13.setName("SEGMENT13");
            promotionDetails1.addSegment(segment13);
            segments.add(segment13);

            entityManager.persist(promotionDetails1);

            //PROMO2
            PromotionDetails promotionDetails2 = new PromotionDetails();
            promotionDetails2.setName("PROMO2");

            Segment segment21 = new Segment();
            segment21.setName("SEGMENT21");
            promotionDetails2.addSegment(segment21);
            segments.add(segment21);

            Segment segment22 = new Segment();
            segment22.setName("SEGMENT22");
            promotionDetails2.addSegment(segment22);
            segments.add(segment22);

            entityManager.persist(promotionDetails2);

            //PROMO3
            PromotionDetails promotionDetails3 = new PromotionDetails();
            promotionDetails3.setName("PROMO3");

            Segment segment31 = new Segment();
            segment31.setName("SEGMENT31");
            promotionDetails3.addSegment(segment31);
            segments.add(segment31);

            Segment segment32 = new Segment();
            segment32.setName("SEGMENT32");
            promotionDetails3.addSegment(segment32);
            segments.add(segment32);

            Segment segment33 = new Segment();
            segment33.setName("SEGMENT33");
            promotionDetails3.addSegment(segment33);
            segments.add(segment33);

            entityManager.persist(promotionDetails3);
            entityManager.flush();

            String msisdn = "1200000000";
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

                entityManager.persist(segmentSubscriber);

                if(i % 10000 == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }

            }
            return 1;
        });

        long stopTime = System.currentTimeMillis();
        double elapsedTime = stopTime - startTime;
        System.out.println("****" + elapsedTime/1000.0);

//        while (true){}
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
