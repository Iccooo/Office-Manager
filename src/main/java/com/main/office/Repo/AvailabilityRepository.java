package com.main.office.Repo;

import com.main.office.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    Availability findById(Integer Id);
    @Query("SELECT a FROM Availability a WHERE :currentTime BETWEEN a.startTime AND a.endTime")
    List<Availability> findAvailabilitiesInUseNow();

}