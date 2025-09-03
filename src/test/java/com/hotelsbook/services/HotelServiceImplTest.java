package com.hotelsbook.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelsbook.services.domain.entities.HotelServiceEntity;
import com.hotelsbook.services.dtos.ServicesByHotelsDto;
import com.hotelsbook.services.dtos.responses.HotelServicesResponseDTO;
import com.hotelsbook.services.repository.HotelServiceRepository;
import com.hotelsbook.services.services.HotelServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HotelServiceImplTest {
	
	@InjectMocks
	private HotelServiceImpl hotelServiceImpl;
	
	@Mock
	private HotelServiceRepository hotelServiceRepository;
	
	private List<HotelServiceEntity> mockEntities;
	@Test
	void getServicesByHotels_shouldReturnGroupedServices_whenDataIsPresent() {
		// Arrange (Preracion de datos)
		List<ServicesByHotelsDto> mockEntities = new ArrayList<>();
		
		mockEntities.add( new ServicesByHotelsDto(  1L, "Hotel California", "Aire acondicionado", 3L ) );
		mockEntities.add( new ServicesByHotelsDto(1L, "Hotel California", "Gym", 5L ) );
		mockEntities.add( new ServicesByHotelsDto(1L, "Hotel California", "Parking gratis", 2L ) );
		mockEntities.add( new ServicesByHotelsDto(1L, "Hotel California", "Restaurante", 4L ) );
		mockEntities.add( new ServicesByHotelsDto(1L, "Hotel California", "Wifi gratis", 1L ) );
		// hotel 2
		mockEntities.add( new ServicesByHotelsDto(2L, "Transilvania", "Aire acondicionado", 3L ) );
		mockEntities.add( new ServicesByHotelsDto(2L, "Transilvania", "Gym", 5L ) );
		mockEntities.add( new ServicesByHotelsDto(2L, "Transilvania", "Wifi gratis", 1L ) );
		mockEntities.add( new ServicesByHotelsDto(2L, "Transilvania", "Parking gratis", 2L ) );
		mockEntities.add( new ServicesByHotelsDto(2L, "Transilvania", "Wifi gratis", 1L ) );
		
		when(hotelServiceRepository.getServicesByHotels("1,2")).thenReturn(mockEntities);
		
		//Act (Ejecucion del metodo a probar)
		List<HotelServicesResponseDTO> result = hotelServiceImpl.getServicesByHotels("1,2");
		
		// Assert (Verificaciones)
		assertNotNull(result);
		assertEquals(2, result.size());
		
		// Verificar el primer hotel
		HotelServicesResponseDTO hotelA = result.get(0);
		assertEquals(1L, hotelA.getHotelDTO().getHotelId());
		assertEquals("Hotel California", hotelA.getHotelDTO().getHotelName());
		assertEquals("Aire acondicionado", hotelA.getServicesDTO().get(0).getServiceName());
		assertEquals(3L, hotelA.getServicesDTO().get(0).getServiceId());
		
		// Verificar el 2do hotel
		HotelServicesResponseDTO hotelB = result.get(1);
		assertEquals(2L, hotelB.getHotelDTO().getHotelId());
		assertEquals("Transilvania", hotelB.getHotelDTO().getHotelName());
		assertEquals("Gym", hotelB.getServicesDTO().get(1).getServiceName());
		assertEquals(5L, hotelB.getServicesDTO().get(1).getServiceId());

	}
	
	@Test
	public void getServicesByHotels_shouldReturnEmptyList_whenRepositoryReturnsEmpty() {
		//Arrange
		when(hotelServiceRepository.getServicesByHotels("1,2")).thenReturn(new ArrayList<>());
		
		//act
		List<HotelServicesResponseDTO> result = hotelServiceImpl.getServicesByHotels("1,2");
		
		//assert
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void getServicesByHotels_shouldReturnEmptyList_whenHotelsIdIsNull() {
		//arrange
		when(hotelServiceRepository.getServicesByHotels(null)).thenReturn(new ArrayList<>());
		
		//act
		List<HotelServicesResponseDTO> result = hotelServiceImpl.getServicesByHotels(null);
		
		//Assert
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void getServicesByHotels_shouldReturnEmptyList_whenHotelsIdIsEmpty() {
		//arrange
		when(hotelServiceRepository.getServicesByHotels("")).thenReturn(new ArrayList<>());
		
		// act
		List<HotelServicesResponseDTO> result = hotelServiceImpl.getServicesByHotels("");
		
		//assert
		assertNotNull(result);
		assertTrue(result.isEmpty());
		
	}

}
