package com.example.BusReservation.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.BusReservation.models.Bookings;
import com.example.BusReservation.models.Seat;

public interface BookingRepository extends CrudRepository<Bookings,Integer> {

	
	@Query("FROM Bookings WHERE bus_id = ?1 and DATE(jurny_date) = ?2")
    List<Bookings> findByIdDate(int bus_id,Date date);

	@Query("select e.seat_number from Bookings e where e.bus_id = ?1 and DATE(e.jurny_date) = ?2")
    List<Integer> findId(int bus_id,Date date);


	@Query("FROM Bookings WHERE user_id = ?1 and DATE(jurny_date) >= ?2")
    List<Bookings> findUpcomingBookings(int user_id,Date date);

	@Query("FROM Bookings WHERE user_id = ?1 and DATE(jurny_date) < ?2")
    List<Bookings> findPastBookings(int user_id,Date date);
	
	@Query("FROM Bookings")
    List<Bookings> selectAll();
	
	@Query("select count(*) From Bookings")
	int bookings_Count();
	
	@Query("FROM Bookings WHERE MONTH(date_of_booking) = MONTH(CURRENT_DATE()) AND YEAR(date_of_booking) = YEAR(CURRENT_DATE())")
    List<Bookings> selectMonthly();
	
	@Query("From Bookings Where date_of_booking=CURRENT_DATE()")
	List<Bookings> selectDaily();
	
	@Query("FROM Bookings WHERE WEEK (date_of_booking) = WEEK( current_date ) - 1 AND YEAR(date_of_booking) = YEAR( current_date )")
	List<Bookings> selectWeekly();

	@Query("select bus_id ,count(*) from Bookings group by bus_id ") 
	  List<Object[]> findBus_id();
	  

		@Query("select DISTINCT ticket_id from Bookings WHERE user_id = ?1 and DATE(jurny_date) < ?2") 
		  List<String> findPastTickets(int user_id,Date date);
		  
		@Query("select DISTINCT ticket_id from Bookings WHERE user_id = ?1 and DATE(jurny_date) >= ?2") 
		  List<String> findUpcomingTickets(int user_id,Date date);
		   
}
