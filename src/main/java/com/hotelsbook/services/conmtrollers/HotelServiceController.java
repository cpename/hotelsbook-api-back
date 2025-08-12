package com.hotelsbook.services.conmtrollers;

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

@RestController
@RequestMapping("api/hotels")
public class HotelServiceController {
	
	@Autowired
	private IHotelService hotelService;
	
	@GetMapping("/services/{hotelIds}")
	public ResponseEntity<?> getHotelServices( @PathVariable("hotelIds") String hotelIds ){
		
		try {
			List<HotelServicesResponseDTO> response = hotelService.getServicesByHotels(hotelIds);
			
			if( response.isEmpty() ) {
				return new ResponseEntity<>(new ErrorResponse(404, ""), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse(500, "Error interno");			
			e.printStackTrace();
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
				
	}

}
