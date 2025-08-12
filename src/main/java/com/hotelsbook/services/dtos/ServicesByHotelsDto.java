package com.hotelsbook.services.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicesByHotelsDto {
		
	private Long hotelId;
    private String hotelName;
    private String serviceName;
    private Long serviceId;
}
