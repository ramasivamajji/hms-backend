package com.hms.repositories;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.entities.HmsUsers;

@Repository
public interface HmsUsersRepo extends JpaRepository<HmsUsers, String> {
	
	@Query(nativeQuery = true, value = "select count(*) from hms_users where user_email=:user_email ")
	Integer checkUser(@Param("user_email") String user_email);
	
	@Query(nativeQuery = true, value = "select count(*) from hms_users where user_email=:user_email and user_password=:user_password")
	Integer getUserCount(@Param("user_email") String user_email,@Param("user_password") String user_password);

	@Query(nativeQuery = true, value = "select * from hms_services where service_for=:service_for order by priority")
	List<Map<String, Object>> getServices(@Param("service_for") String service_for);
	
	@Query(nativeQuery = true, value = "select nextval('booking_id')")
	Integer getBookingId();
	
	@Query(nativeQuery = true, value = "select * from hms_rooms_mst where is_active order by room_id")
	List<Map<String, Object>> getRooms();
	
	@Query(nativeQuery = true, value = "select booking_id,persons_count,stay_from,stay_upto,room_name,price,\r\n"
			+ "case when is_approoved=true then 'Approved' when is_approoved=false then 'Rejected' else 'Pending' end as hot_cnf,\r\n"
			+ "case when is_approoved=true then 'btn-success' when is_approoved=false then 'btn-danger' else 'btn-secondary' end as hot_cnf_col\r\n"
			+ "from hms_room_bookings a\r\n"
			+ "inner join hms_rooms_mst b on a.room_id=b.room_id\r\n"
			+ "where user_email=:user_email order by booking_id")
	List<Map<String, Object>> getBookings(@Param("user_email") String user_email);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from hms_room_bookings where booking_id=:booking_id")
	Integer deleteBooking(@Param("booking_id") Integer booking_id);
	
	@Query(nativeQuery = true, value = "select user_email,booking_id,persons_count,stay_from,stay_upto,room_name,price,\r\n"
			+ "case when is_approoved=true then 'Approved' when is_approoved=false then 'Rejected' else 'Pending' end as hot_cnf,\r\n"
			+ "case when is_approoved=true then 'btn-success' when is_approoved=false then 'btn-danger' else 'btn-primary' end as hot_cnf_col\r\n"
			+ "from hms_room_bookings a\r\n"
			+ "inner join hms_rooms_mst b on a.room_id=b.room_id order by booking_id")
	List<Map<String, Object>> getAllBookings();
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update hms_room_bookings set is_approoved=:is_approoved where booking_id=:booking_id")
	Integer updateBooking(@Param("is_approoved") Boolean is_approoved,@Param("booking_id") Integer booking_id);
}
