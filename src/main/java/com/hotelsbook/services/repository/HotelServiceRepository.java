package com.hotelsbook.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.hotelsbook.services.domain.entities.HotelServiceEntity;
import com.hotelsbook.services.dtos.ServicesByHotelsDto;


public interface HotelServiceRepository extends JpaRepository<HotelServiceEntity, Long> {
	
	@Procedure(name = "serviciosPorHoteles")
	List<ServicesByHotelsDto> getServicesByHotels(@Param("hotelIds") String hotelIds);


}
