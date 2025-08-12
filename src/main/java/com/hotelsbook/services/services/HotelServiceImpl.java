package com.hotelsbook.services.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotelsbook.services.domain.entities.HotelServiceEntity;
import com.hotelsbook.services.dtos.HotelDTO;
import com.hotelsbook.services.dtos.ServiceDto;
import com.hotelsbook.services.dtos.ServicesByHotelsDto;
import com.hotelsbook.services.dtos.responses.HotelServicesResponseDTO;
import com.hotelsbook.services.repository.HotelServiceRepository;
import com.hotelsbook.services.services.interfaces.IHotelService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelServiceImpl implements IHotelService {
	
	@Autowired
	private HotelServiceRepository hotelServiceRepository;
	
	@Transactional
	public List<HotelServicesResponseDTO> getServicesByHotels(String hotelsId) {
		
		List<ServicesByHotelsDto> servicesByHotels = hotelServiceRepository.getServicesByHotels(hotelsId);
		
		Map<Long, HotelServicesResponseDTO> hotelServicesMap = new LinkedHashMap<>();
		
		for (ServicesByHotelsDto  servicesByHotelsDto : servicesByHotels) {
			HotelDTO hotelDto = new HotelDTO();
			ServiceDto serviceDto = new ServiceDto();

			
//			hoteles 
			hotelDto.setHotelId(servicesByHotelsDto.getHotelId());
			hotelDto.setHotelName(servicesByHotelsDto.getHotelName());
//			servicios hotel
			serviceDto.setServiceName(servicesByHotelsDto.getServiceName());
			serviceDto.setServiceId(servicesByHotelsDto.getServiceId());
			
			HotelServicesResponseDTO hotelServices = hotelServicesMap.getOrDefault(hotelDto.getHotelId(), 
					new HotelServicesResponseDTO());
			
			hotelServices.setHotelDTO(hotelDto);
			hotelServices.getServicesDTO().add(serviceDto);
			hotelServicesMap.put(hotelDto.getHotelId(), hotelServices);			

		}

		return new ArrayList<>(hotelServicesMap.values());
		
		
	}

}
