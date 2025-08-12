package com.hotelsbook.services.services.interfaces;

import java.util.List;

import com.hotelsbook.services.dtos.responses.HotelServicesResponseDTO;

public interface IHotelService {
	
	public List<HotelServicesResponseDTO> getServicesByHotels(String hotelsId);
	

}
