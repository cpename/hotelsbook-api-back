package com.hotelsbook.services.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelsbook.services.dtos.responses.HotelServicesResponseDTO;
import com.hotelsbook.services.exceptions.ErrorResponse;
import com.hotelsbook.services.services.interfaces.IHotelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/hotels")
@Slf4j
@Tag(name = "Hotel resources", description = "recupare recursos de hoteles")
public class HotelServiceController {
	
	@Autowired
	private IHotelService hotelService;
	
	@GetMapping("/services/{hotelIds}")
	@Operation(summary = "get los servicios de una lista de hoteles ids separado por comas")
	public ResponseEntity<?> getHotelServices( @PathVariable("hotelIds") String hotelIds ){
		log.info("lista de ids de hoteles: " + hotelIds);
		try {
			List<HotelServicesResponseDTO> response = hotelService.getServicesByHotels(hotelIds);
			
			if( response.isEmpty() ) {
				return new ResponseEntity<>(new ErrorResponse(404, ""), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse(500, "Error interno");
			log.error("Error interno: ");
			e.printStackTrace();
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
				
	}

}
