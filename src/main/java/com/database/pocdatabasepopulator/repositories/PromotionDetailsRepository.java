package com.database.pocdatabasepopulator.repositories;

import com.database.pocdatabasepopulator.entities.PromotionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionDetailsRepository extends JpaRepository<PromotionDetails, Long> {

}
