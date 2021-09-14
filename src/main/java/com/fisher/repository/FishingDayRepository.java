package com.fisher.repository;

import com.fisher.FishingDay.FishingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingDayRepository extends JpaRepository<FishingDay,Long> {

    @Modifying
    @Query(value = "delete from FishingDay f where f.id = :id")
    int deleteFishDay(long id);
}
