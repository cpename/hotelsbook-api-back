package com.hotelsbook.services.dtos.responses;

import java.util.ArrayList;
import java.util.List;


import com.hotelsbook.services.dtos.HotelDTO;
import com.hotelsbook.services.dtos.ServiceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class HotelServicesResponseDTO {
	
	public HotelDTO hotelDTO;
	public List<ServiceDto> servicesDTO = new ArrayList<>();
	
	public void addItem(ServiceDto serviceDto) {
		this.servicesDTO.add(serviceDto);
	}
	

}
