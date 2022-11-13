package com.database.pocdatabasepopulator.repositories;

import com.database.pocdatabasepopulator.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

}
