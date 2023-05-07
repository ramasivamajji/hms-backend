package com.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.entities.HmsRoomBookings;

@Repository
public interface HmsRoomBookingsRepo extends JpaRepository<HmsRoomBookings, Integer> {

}
